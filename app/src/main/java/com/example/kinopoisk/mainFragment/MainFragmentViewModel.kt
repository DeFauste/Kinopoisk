package com.example.kinopoisk.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.kinopoisk.mainFragment.api.RetrofitClient
import com.example.kinopoisk.mainFragment.models.Movies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainFragmentViewModel : ViewModel() {
    private var _newMoviesSharedFlow = MutableSharedFlow<List<Movies>>()
    val newMoviesSharedFlow = _newMoviesSharedFlow.asSharedFlow()
    fun updateMovies() {
        viewModelScope.launch() {
            try {
                val response =
                    RetrofitClient.api.getNewMovies(1, 10)
                        .body()?.docs
                _newMoviesSharedFlow.emit(response ?: arrayListOf())
            } catch (e: IOException) {
                println("onCreate: not internet")
            } catch (e: HttpException) {
                println("HttpException")
            }

        }
    }

    val flowNewMovies = Pager(
        PagingConfig(10,
            enablePlaceholders = false),
        pagingSourceFactory = { NewMoviesPageSource(RetrofitClient.api) }
    ).flow
        .cachedIn(viewModelScope)
}