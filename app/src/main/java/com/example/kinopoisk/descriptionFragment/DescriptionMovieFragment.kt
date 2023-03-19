package com.example.kinopoisk.descriptionFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.R
import com.example.kinopoisk.bookmarksFragment.BookmarksViewModel
import com.example.kinopoisk.database.MoviesData
import com.example.kinopoisk.databinding.FragmentDescriptionMovieBinding
import com.example.kinopoisk.descriptionFragment.Adapter.RecyclerAdapterPerson
import com.example.kinopoisk.descriptionFragment.models.modelsDescr.Doc
import kotlinx.coroutines.launch


class DescriptionMovieFragment : Fragment() {
    private var _binding: FragmentDescriptionMovieBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: DescriptionFragmentViewModel by activityViewModels()

    private val bookmarksViewModel: BookmarksViewModel
        get() = (activity as MainActivity).bookmarksViewModel

    private var pair: Pair<Int, Int> =
        Pair(R.id.action_descriptionMovieFragment2_to_mainFragment, 666)
    private val adapter = RecyclerAdapterPerson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var trailer = ""
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
            findNavController().navigate(pair.first)
        }
        binding.buttonTrailer.setOnClickListener {
            if (trailer.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer)
                requireActivity().startActivity(intent)
            } else Toast.makeText(requireActivity(), getString(R.string.toast_trailer), Toast.LENGTH_SHORT).show()
        }
    }

    private fun addBookmarks(movie: Doc) {
        binding.topMenu.addBtn.setOnClickListener {
            with(binding) {
                val m = MoviesData(
                    id = movie.id,
                    description = descriptionMovie.text.toString(),
                    name = nameMovie.text.toString(),
                    alternativeName = alternativeNameMovie.text.toString(),
                    country = binding.layoutDescription.countryMovie.text.toString(),
                    length = movie.movieLength.toString(),
                    poster = movie.poster.url,
                    ratingIMB = binding.layoutDescription.ratingIMDB.text.toString(),
                    ratingKP = binding.layoutDescription.ratingKP.text.toString(),
                    trailer = trailer,
                    year = movie.year.toString(),
                    type = movie.type
                )
                bookmarksViewModel.addMovie(m)
            }
        }
    }

    private fun updatePair() {
        lifecycleScope.launch() {
            fragmentViewModel.flowState.collect() {
                pair = it
            }
            descriptionUpdate()
            updatePerson()
        }
    }

    private fun updatePerson() {
        fragmentViewModel.getPersons(pair.second)
        binding.recyclerPerson.adapter = adapter
        lifecycleScope.launchWhenCreated {
            fragmentViewModel.persons.collect() { persons ->
                if (persons.isNotEmpty()) {
                    adapter.movies = persons
                }
            }
        }
    }

    private fun descriptionUpdate() {
        fragmentViewModel.getMovie(pair.second)

        lifecycleScope.launch() {
            fragmentViewModel.movie.collect() { response ->
                if (response != null) {
                    val movie = response.docs[0]
                    with(binding) {
                        addBookmarks(movie)
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
                    trailer = try {
                        movie.videos.trailers[0].url
                    } catch (e: java.lang.Exception) {
                        ""
                    }
                    println(trailer)
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
    }
}