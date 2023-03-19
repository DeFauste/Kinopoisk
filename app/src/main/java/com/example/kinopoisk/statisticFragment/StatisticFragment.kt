package com.example.kinopoisk.statisticFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.bookmarksFragment.BookmarksViewModel
import com.example.kinopoisk.database.MoviesData
import com.example.kinopoisk.databinding.FragmentStatisticBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow


class StatisticFragment : Fragment() {
    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding!!

    private val bookmarksViewModel: BookmarksViewModel
        get() = (activity as MainActivity).bookmarksViewModel

    private var _flowF = MutableSharedFlow<Boolean>()
    val flowF = _flowF.asSharedFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawPieDiagram()
    }

    private fun drawPieDiagram() {
        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("", 0))
        pie.data(data)
        val anyChartView = binding.anyChartView
        anyChartView.setChart(pie)

        val myThread = Thread() // создаём новый поток
        {
            val movieCount = bookmarksViewModel.getCount("movie")
            val seriesCount = bookmarksViewModel.getCount("tv-series")
            val cartoonCount = bookmarksViewModel.getCount("cartoon")
            val animeCount = bookmarksViewModel.getCount("anime")
            val tvShowsCount = bookmarksViewModel.getCount("tv-show")
            data.clear()
            data.add(ValueDataEntry("movie", movieCount))
            data.add(ValueDataEntry("series", seriesCount))
            data.add(ValueDataEntry("cartoon", cartoonCount))
            data.add(ValueDataEntry("anime", animeCount))
            data.add(ValueDataEntry("tv-show", tvShowsCount))
            println("### $tvShowsCount")
            lifecycleScope.launchWhenCreated {
                _flowF.emit(true)
            }
        }.start()
        lifecycleScope.launch {
            flowF.collect() {
                pie.data(data)
                anyChartView.setChart(pie)
                anyChartView.invalidate()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}