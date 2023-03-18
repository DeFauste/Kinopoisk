package com.example.kinopoisk.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.kinopoisk.mainFragment.api.RetrofitClient
import com.example.kinopoisk.mainFragment.innerFragment.newM.NewMoviesPageSource
import com.example.kinopoisk.mainFragment.innerFragment.serialsMovies.TypeMoviesPageSource
import com.example.kinopoisk.mainFragment.innerFragment.top.TopMoviesPageSource
import com.example.kinopoisk.mainFragment.model.Movies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
}