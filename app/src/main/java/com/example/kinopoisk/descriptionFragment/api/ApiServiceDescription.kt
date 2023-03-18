package com.example.kinopoisk.descriptionFragment.api

import com.example.kinopoisk.TOKEN_API
import com.example.kinopoisk.descriptionFragment.models.modelForDescription.ResponsesDescription
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceDescription {
    @GET("movie?")
    suspend fun getMovie(
        @Query("id") id: Int = 666,
        @Query("token") token:String = TOKEN_API
    ) : Response<ResponsesDescription>
}