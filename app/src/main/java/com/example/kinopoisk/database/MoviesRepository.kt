package com.example.kinopoisk.database

import kotlinx.coroutines.flow.Flow

class MoviesRepository(private val moviesDao: MoviesDao) {
    val readAllMovies: Flow<List<MoviesData>> = moviesDao.readAllMovies()

    suspend fun addMovies(moviesData: MoviesData) {
        moviesDao.addMovies(moviesData)
    }

    fun searchMovies(movieName: String): Flow<List<MoviesData>> {
       return moviesDao.search(movieName)
    }

    suspend fun deleteMovies(moviesData: MoviesData) {
        moviesDao.deleteMovies()
    }

    fun getCount(value: String) = moviesDao.getCount(value)
    suspend fun checkMovie(idValue: String): Boolean = moviesDao.checkMovie(idValue)
}