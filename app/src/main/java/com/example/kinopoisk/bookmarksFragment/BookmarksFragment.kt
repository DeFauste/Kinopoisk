package com.example.kinopoisk.bookmarksFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.R
import com.example.kinopoisk.database.MoviesData
import com.example.kinopoisk.databinding.FragmentBookmarksBinding


class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    private val bookmarksViewModel: BookmarksViewModel
        get() = (activity as MainActivity).bookmarksViewModel

    private val adapter = RecyclerAdapterBookmarksMovie(object : onClickListenerBookmarksMovie {
        override fun onCLick(moviesData: MoviesData) {

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
                adapter.movies = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}