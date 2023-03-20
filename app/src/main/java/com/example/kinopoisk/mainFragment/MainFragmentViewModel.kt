package com.example.kinopoisk.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.kinopoisk.mainFragment.api.RetrofitClient
import com.example.kinopoisk.mainFragment.innerFragment.dynamicSerach.DynamicSearchPageSource
import com.example.kinopoisk.mainFragment.innerFragment.newM.NewMoviesPageSource
import com.example.kinopoisk.mainFragment.innerFragment.serialsMovies.TypeMoviesPageSource
import com.example.kinopoisk.mainFragment.innerFragment.top.TopMoviesPageSource

class MainFragmentViewModel : ViewModel() {

    val flowNewMovies = Pager(
        PagingConfig(10,
            enablePlaceholders = false),
        pagingSourceFactory = { NewMoviesPageSource(RetrofitClient.apiNewMovies) }
    ).flow
        .cachedIn(viewModelScope)

    val flowTopMovies = Pager(
        PagingConfig(10,
            enablePlaceholders = false),
        pagingSourceFactory = { TopMoviesPageSource(RetrofitClient.apiTopMovie) }
    ).flow
        .cachedIn(viewModelScope)

    val flowTypeMovie = Pager(
        PagingConfig(10,
            enablePlaceholders = false),
        pagingSourceFactory = { TypeMoviesPageSource(RetrofitClient.apiTypeMovie, "movie") }
    ).flow
        .cachedIn(viewModelScope)

    val flowTypeSeries = Pager(
        PagingConfig(10,
            enablePlaceholders = false),
        pagingSourceFactory = { TypeMoviesPageSource(RetrofitClient.apiTypeMovie, "tv-series") }
    ).flow
        .cachedIn(viewModelScope)

    fun flowDynamicSearch(name: String) = Pager(
        PagingConfig(pageSize = 10,
            enablePlaceholders = false),
        pagingSourceFactory = { DynamicSearchPageSource(RetrofitClient.apiDynamicSearch, name) }
    ).flow.cachedIn(viewModelScope)
}