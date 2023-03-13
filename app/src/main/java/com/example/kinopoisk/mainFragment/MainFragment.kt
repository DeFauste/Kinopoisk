package com.example.kinopoisk.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.kinopoisk.databinding.FragmentMainBinding
import com.example.kinopoisk.mainFragment.innerFragment.movies.MoviesFragment
import com.example.kinopoisk.mainFragment.innerFragment.newM.NewMovieFragment
import com.example.kinopoisk.mainFragment.innerFragment.serials.SerialsFragment
import com.example.kinopoisk.mainFragment.innerFragment.top.TopMoviesFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class MainFragment : Fragment() {

    private val fragList = listOf(
        NewMovieFragment.newInstance(),
        TopMoviesFragment.newInstance(),
        MoviesFragment.newInstance(),
        SerialsFragment.newInstance()
    )
    private val fragName = listOf(
        "New",
        "Top 100",
        "Movies",
        "Serials"
    )
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        initTabLayout()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initTabLayout() {
        val adapter = VpAdapterMain(this, fragList)
        binding.viewPagMain.adapter = adapter
        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagMain) { tab, pos ->
            tab.text = fragName[pos]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}