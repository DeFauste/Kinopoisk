package com.example.kinopoisk.bookmarksFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.R
import com.example.kinopoisk.database.MoviesData
import com.example.kinopoisk.databinding.FragmentBookmarksBinding
import com.example.kinopoisk.descriptionFragment.DescriptionFragmentViewModel
import com.example.kinopoisk.extensions.hideKeyboard
import java.util.*


class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    private val bookmarksViewModel: BookmarksViewModel
        get() = (activity as MainActivity).bookmarksViewModel

    private var timer: Timer = Timer()
    private val DELAY: Long = 1000

    private val fragmentDescriptionViewModel: DescriptionFragmentViewModel by activityViewModels()

    private val adapter = RecyclerAdapterBookmarksMovie(object : onClickListenerBookmarksMovie {
        override fun onCLick(moviesData: MoviesData) {
            fragmentDescriptionViewModel.stateFragmentDescription(R.id.action_descriptionMovieFragment2_to_bookmarksFragment, moviesData.id)
            findNavController().navigate(R.id.action_bookmarksFragment_to_descriptionMovieFragment2)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateRecyclerView()
        searchMovies()
        openSearch()
        closeSearch()
    }

    private fun initRecyclerView() {
        binding.recyclerBookmarks.adapter = adapter
        binding.recyclerBookmarks.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun updateRecyclerView() {
        bookmarksViewModel.getBookmarks()
        lifecycleScope.launchWhenCreated {
            bookmarksViewModel.bookmarksMovie.collect() {
                if (it.isNotEmpty())
                    adapter.movies = it
                else
                    adapter.movies = emptyList()
            }
        }
    }

    private fun searchMovies() {
        binding.searchBar.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                requireContext().hideKeyboard(requireView())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                timer.cancel()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        lifecycleScope.launchWhenCreated {
                            bookmarksViewModel.searchMovie("%$newText%")
                            bookmarksViewModel.searchBookmarksMovie.collect() {
                                adapter.movies = it
                            }
                        }
                    }
                }, DELAY)

                return true
            }
        })
    }

    private fun openSearch() {
        with(binding) {
            searchBar.searchView.setOnSearchClickListener {
                searchBar.exitSearch.visibility = View.VISIBLE
            }
            searchBar.searchViewHelp.setOnClickListener {
                searchBar.exitSearch.visibility = View.VISIBLE
                it.visibility = View.GONE
                searchBar.searchView.isIconified = false
                searchBar.searchView.requestFocus()
            }
        }
    }

    private fun closeSearch() {
        with(binding) {
            searchBar.exitSearch.setOnClickListener {
                with(searchBar) {
                    exitSearch.visibility = android.view.View.GONE
                    searchView.clearFocus()
                    searchView.setQuery("", false)
                    searchView.isIconified = true
                    searchViewHelp.visibility = android.view.View.VISIBLE
                }
                requireContext().hideKeyboard(searchBar.searchView)
            }
        }
        updateRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}