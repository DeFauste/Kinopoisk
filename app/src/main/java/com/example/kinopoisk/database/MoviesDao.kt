package com.example.kinopoisk.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(moviesData: MoviesData)

    @Query("SELECT * FROM bookmarks_table ORDER BY id ASC")
    fun readAllMovies(): Flow<List<MoviesData>>

//    @Query("SELECT * FROM bookmarks_table WHERE name=:movieName")
//    suspend fun search(movieName: String): Flow<List<MoviesData>>?

    @Query("DELETE FROM bookmarks_table")
    suspend fun deleteMovies()
}