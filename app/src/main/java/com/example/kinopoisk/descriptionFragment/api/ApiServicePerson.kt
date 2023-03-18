package com.example.kinopoisk.descriptionFragment.api

import com.example.kinopoisk.TOKEN_API
import com.example.kinopoisk.descriptionFragment.models.modelPersons.ResponsesPerson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicePerson {
    @GET("movie?selectFields=persons&page=1")
    suspend fun getPersons(
        @Query("id") id: Int,
        @Query("token") token:String = TOKEN_API
    ) : Response<ResponsesPerson>
}