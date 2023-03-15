package com.example.kinopoisk.descriptionFragment.api

import com.example.kinopoisk.descriptionFragment.models.modelPersons.ResponsesPersons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicePerson {
    @GET("movie?selectFields=persons&page=1")
    suspend fun getPersons(
        @Query("id") id: Int,
        @Query("token") token:String = "C2YM9N6-ECGMM50-GFAA00K-Y7FSNBQ"
    ) : Response<ResponsesPersons>
}