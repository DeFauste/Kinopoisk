package com.example.kinopoisk.mainFragment.innerFragment.serialsMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentMoviesBinding
import com.example.kinopoisk.databinding.FragmentNewMovieBinding
import com.example.kinopoisk.mainFragment.MainFragmentViewModel
import com.example.kinopoisk.mainFragment.innerFragment.adpter.RecyclerAdapterMovie
import com.example.kinopoisk.mainFragment.innerFragment.adpter.onClickListenerMovie
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: MainFragmentViewModel by activityViewModels()

    private val pagingAdapter = RecyclerAdapterMovie(object : onClickListenerMovie {
        override fun onCLick(id: Int) {
            fragmentViewModel.stateFragmentDescription(true, id)
        }
    })
    private lateinit var jobMovies: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        /*hide action bar*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            fragmentViewModel.flowTypeMovie.collectLatest { pagingData  ->
                pagingAdapter.submitData(pagingData)
            }
        }
        binding.recyclerMovies.adapter = pagingAdapter
        binding.recyclerMovies.layoutManager = LinearLayoutManager(requireActivity())
    }


    companion object {
        @JvmStatic
        fun newInstance() = MoviesFragment()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}