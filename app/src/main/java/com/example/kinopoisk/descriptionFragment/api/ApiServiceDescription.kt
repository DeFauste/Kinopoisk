package com.example.kinopoisk.descriptionFragment.api

import com.example.kinopoisk.TOKEN_API
import com.example.kinopoisk.descriptionFragment.models.modelsDescr.ResponsesDescr
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceDescription {
    @GET("movie?selectFields=id&selectFields=type&selectFields=movieLength&selectFields=countries&selectFields=name&selectFields=alternativeName&%20selectFields=premiere.country&selectFields=year&selectFields=description&selectFields=rating.imdb&selectFields=rating.kp&selectFields=poster.url&%20selectFields=poster.previewUrl&selectFields=countries&selectFields=videos.trailers")
    suspend fun getMovie(
        @Query("id") id: Int = 666,
        @Query("token") token:String = TOKEN_API
    ) : Response<ResponsesDescr>
}