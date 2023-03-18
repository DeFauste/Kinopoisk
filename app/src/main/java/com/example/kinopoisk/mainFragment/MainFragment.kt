package com.example.kinopoisk.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.databinding.FragmentMainBinding
import com.example.kinopoisk.descriptionFragment.DescriptionFragmentViewModel
import com.example.kinopoisk.mainFragment.innerFragment.adpter.RecyclerAdapterSearch
import com.example.kinopoisk.mainFragment.innerFragment.adpter.onClickListenerMovie
import com.example.kinopoisk.mainFragment.innerFragment.newM.NewMovieFragment
import com.example.kinopoisk.mainFragment.innerFragment.serialsMovies.MoviesFragment
import com.example.kinopoisk.mainFragment.innerFragment.serialsMovies.SerialsFragment
import com.example.kinopoisk.mainFragment.innerFragment.top.TopMoviesFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import java.util.*


class MainFragment : Fragment() {
    private val fragmentViewModel: MainFragmentViewModel by activityViewModels()
    private val fragmentDescriptionViewModel: DescriptionFragmentViewModel by activityViewModels()

    private val adapterSearch = RecyclerAdapterSearch(object : onClickListenerMovie {
        override fun onCLick(id: Int) {
            fragmentDescriptionViewModel.stateFragmentDescription(true, id)
        }
    })

    private var timer: Timer = Timer()
    private val DELAY: Long = 1000

    private val fragList = listOf(
        NewMovieFragment.newInstance(),
        TopMoviesFragment.newInstance(),
        MoviesFragment.newInstance(),
        SerialsFragment.newInstance()
    )
    private val fragName = listOf(
        "New",
        "Top 250",
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

        binding.recyclerSearch.adapter = adapterSearch
        binding.recyclerSearch.layoutManager = LinearLayoutManager(requireActivity())

        searchMovies()

    }

    private fun initTabLayout() {
        val adapter = VpAdapterMain(this, fragList)
        binding.viewPagMain.adapter = adapter
        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagMain) { tab, pos ->
            tab.text = fragName[pos]
        }.attach()
        checkStateDescriptionFragment()
    }

    private fun checkStateDescriptionFragment() {
        lifecycleScope.launch {
            fragmentDescriptionViewModel.stateFragmentDescription.collect() {
                if (it.first) {
                    binding.fragmentDescription.visibility = View.VISIBLE
                } else {
                    binding.fragmentDescription.visibility = View.GONE
                }
            }
        }
    }


    private fun searchMovies() {
        binding.searchBarLayout.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(timer != null)
                    timer.cancel()

                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        lifecycleScope.launchWhenCreated {
                            if (newText != null && newText.isNotEmpty() && newText.length > 3) {
                                Toast.makeText(requireContext(), "$newText", Toast.LENGTH_SHORT).show()
                                fragmentViewModel.flowDynamicSearch(newText.toString()).collect() {
                                    adapterSearch.submitData(it)
                                }
                            }
                        }
                    }
                }, DELAY)

                return true
            }
        })
    }

    private fun closeSearch() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}