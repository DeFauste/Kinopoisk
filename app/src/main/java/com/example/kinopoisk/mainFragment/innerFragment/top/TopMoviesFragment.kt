package com.example.kinopoisk.mainFragment.innerFragment.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentTopMoviesBinding
import com.example.kinopoisk.descriptionFragment.DescriptionFragmentViewModel
import com.example.kinopoisk.mainFragment.MainFragmentViewModel
import com.example.kinopoisk.mainFragment.innerFragment.adpter.LoadMoreAdapter
import com.example.kinopoisk.mainFragment.innerFragment.adpter.RecyclerAdapterTopMovie
import com.example.kinopoisk.mainFragment.innerFragment.adpter.onClickListenerMovie
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TopMoviesFragment : Fragment() {
    private var _binding: FragmentTopMoviesBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: MainFragmentViewModel by activityViewModels()
    private val fragmentDescriptionViewModel: DescriptionFragmentViewModel by activityViewModels()

    private lateinit var jobRecycler: Job

    private val pagingAdapter = RecyclerAdapterTopMovie(object : onClickListenerMovie {
        override fun onCLick(id: Int) {
            findNavController().navigate(R.id.action_mainFragment_to_descriptionMovieFragment2)
            fragmentDescriptionViewModel.stateFragmentDescription(R.id.action_descriptionMovieFragment2_to_mainFragment, id)
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
        _binding = FragmentTopMoviesBinding.inflate(inflater, container, false)
        /*hide action bar*/
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobRecycler = lifecycleScope.launch {
           fragmentViewModel.flowTopMovies.collectLatest { pagingData  ->
                pagingAdapter.submitData(pagingData)
            }
        }
        binding.recyclerTopMovie.adapter = pagingAdapter.withLoadStateFooter(
            LoadMoreAdapter {
                pagingAdapter.retry()
            }
        )
        binding.recyclerTopMovie.layoutManager = LinearLayoutManager(requireActivity())

        load()
    }
    private fun load() {
        lifecycleScope.launchWhenCreated {
            pagingAdapter.loadStateFlow.collect() {
                val state = it.refresh
                binding.progressLoad.isVisible = state is LoadState.Loading
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = TopMoviesFragment()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        jobRecycler.cancel()
        _binding = null
    }
}