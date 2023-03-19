package com.example.kinopoisk.bookmarksFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.database.MoviesData
import com.example.kinopoisk.database.MoviesDatabase
import com.example.kinopoisk.database.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookmarksViewModel : ViewModel() {
    private lateinit var repository: MoviesRepository

    fun initDatabase(context: Context) {
        val moviesDao = MoviesDatabase.getDatabase(context).movieDao()
        repository = MoviesRepository(moviesDao)
    }

    fun addMovie(moviesData: MoviesData) {
        viewModelScope.launch {
            repository.addMovies(moviesData)
        }
    }

    private var _sharedBookmarks = MutableSharedFlow<List<MoviesData>>()
    val bookmarksMovie = _sharedBookmarks.asSharedFlow()
    fun getBookmarks() {
        viewModelScope.launch {
            repository.readAllMovies.collect() {
                _sharedBookmarks.emit(it)
            }
        }
    }

    private var _searchBookmarks = MutableSharedFlow<List<MoviesData>>()
    val searchBookmarksMovie = _searchBookmarks.asSharedFlow()
    fun searchMovie(movieName: String) {
        viewModelScope.launch {
            repository.searchMovies(movieName).collect(){
                _searchBookmarks.emit(it)
            }
        }
    }

     fun getCount(value: String) = repository.getCount(
        value
    )
}