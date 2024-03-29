package com.example.kinopoisk.mainFragment.api

import androidx.annotation.IntRange
import com.example.kinopoisk.TOKEN_API
import com.example.kinopoisk.mainFragment.model.recyclerLoad.ResponsesApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNewMovie {
    @GET("movie?" +
            "selectFields=id" +
            "&selectFields=name&" +
            "selectFields=alternativeName&" +
            "selectFields=premiere.country&" +
            "selectFields=year&" +
            "selectFields=description&" +
            "selectFields=rating.imdb&" +
            "selectFields=poster.url&" +
            "selectFields=poster.previewUrl&" +
            "selectFields=countries&" +
            "sortField=year&sortType=-1&" +
            "status=completed&" +
            "name=!null&" +
            "poster.url=!null&" +
            "token=$TOKEN_API")
    suspend fun getMovies(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("limit") @IntRange(from = 1, to = 10) limit: Int = 10,
        @Query("year") year: String = "1860-2023"
    ) :Response<ResponsesApi>
}