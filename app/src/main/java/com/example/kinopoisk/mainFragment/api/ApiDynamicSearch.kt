package com.example.kinopoisk.mainFragment.api

import com.example.kinopoisk.mainFragment.models.ResponsesSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDynamicSearch {
    @GET("movie?selectFields=id&selectFields=name&selectFields=alternativeName&%20selectFields=premiere.country&selectFields=year&selectFields=description&selectFields=rating.imdb&selectFields=poster.url&selectFields=poster.previewUrl&selectFields=countries&token=C2YM9N6-ECGMM50-GFAA00K-Y7FSNBQ")
    suspend fun getMovies(
        @Query("name") name:String
    ) :Response<ResponsesSearch>

}