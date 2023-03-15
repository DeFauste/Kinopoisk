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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class DescriptionMovieFragment : Fragment() {
    private var _binding: FragmentDescriptionMovieBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: DescriptionFragmentViewModel by activityViewModels()

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
        descriptionUpdate()

        binding.recyclerPerson.adapter = adapter
        binding.recyclerPerson.layoutManager = LinearLayoutManager(requireActivity())

    }
    private fun updatePerson() {
        lifecycleScope.launchWhenCreated {

        }
    }
    private fun descriptionUpdate() {
        fragmentViewModel.getMovie()

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