package com.example.kinopoisk.bookmarksFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.database.MoviesData
import com.example.kinopoisk.database.MoviesDatabase
import com.example.kinopoisk.database.MoviesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
}