package com.example.kinopoisk.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks_table")
data class MoviesData(
    @PrimaryKey
    val id: Int,
    val name: String,
    val alternativeName: String,
    val description: String,
    val poster: String,
    val country: String,
    val year: String,
    val length: String,
    val trailer: String,
    val ratingIMB: String,
    val ratingKP: String,
    val type: String
)