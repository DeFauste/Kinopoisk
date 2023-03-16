package com.example.kinopoisk.descriptionFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentDescriptionMovieBinding
import com.example.kinopoisk.descriptionFragment.Adapter.RecyclerAdapterPerson
import com.example.kinopoisk.mainFragment.MainFragmentViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class DescriptionMovieFragment : Fragment() {
    private var _binding: FragmentDescriptionMovieBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: DescriptionFragmentViewModel by activityViewModels()
    private val mainFragmentViewModel: MainFragmentViewModel by activityViewModels()

    private var pair: Pair<Boolean,Int> = Pair(false, 666)
    private val adapter = RecyclerAdapterPerson()

    private lateinit var jobMovie: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDescriptionMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updatePair()
        binding.topMenu.backButton.setOnClickListener {
            mainFragmentViewModel.stateFragmentDescription(false, pair.second)
        }
    }

    private fun updatePair() {
        lifecycleScope.launchWhenCreated {
            mainFragmentViewModel.stateFragmentDescription.collect() {
                pair = it
                descriptionUpdate()
                updatePerson()
            }
        }
    }
    private fun updatePerson() {
        fragmentViewModel.getPersons(pair.second)
        binding.recyclerPerson.adapter = adapter
        lifecycleScope.launchWhenCreated {
            fragmentViewModel.persons.collect() { persons ->
                if(persons.isNotEmpty()) {
                    adapter.cites = persons
                }
            }
        }
    }
    private fun descriptionUpdate() {
        fragmentViewModel.getMovie(pair.second)

        jobMovie = lifecycleScope.launch() {
            fragmentViewModel.movie.collect() { response ->
                if (response != null) {
                    with(binding) {
                        val movie = response.docs[0]
                        loadPoster(movie.poster.url)
                        nameMovie.text = movie.name
                        alternativeNameMovie.text = movie.alternativeName
                        layoutDescription.ratingIMDB.text = movie.rating.imdb.toString()
                        layoutDescription.ratingKP.text = movie.rating.kp.toString()
                        layoutDescription.countryMovie.text = movie.countries[0].name.toString()
                        layoutDescription.dateMovie.text = movie.year.toString()
                        layoutDescription.lengthMovie.text = movie.movieLength.toString()
                        layoutDescription.lengthMovie.text = movie.movieLength.toString()
                        descriptionMovie.text = movie.description
                    }
                }
            }
        }
    }

    private fun loadPoster(imgUrl: String) {
        Glide
            .with(binding.nameMovie)
            .load(imgUrl)
            .centerCrop()
            .placeholder(R.drawable.progress_bar)
            .into(binding.posterMovie)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        jobMovie.cancel()
    }
}