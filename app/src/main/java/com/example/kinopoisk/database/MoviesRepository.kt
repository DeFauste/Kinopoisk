package com.example.kinopoisk.database

import kotlinx.coroutines.flow.Flow

class MoviesRepository(private val moviesDao: MoviesDao) {
    val readAllMovies: Flow<List<MoviesData>> = moviesDao.readAllMovies()

    suspend fun addMovies(moviesData: MoviesData) {
        moviesDao.addMovies(moviesData)
    }

//    suspend fun searchMovies(moviesData: MoviesData) {
//        moviesDao.search(moviesData.name)
//    }

    suspend fun deleteMovies(moviesData: MoviesData) {
        moviesDao.deleteMovies()
    }
}