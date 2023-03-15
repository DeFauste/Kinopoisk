package com.example.kinopoisk.mainFragment.api

import androidx.annotation.IntRange
import com.example.kinopoisk.mainFragment.model.ResponsesApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceTypeMovie {
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
            "status=completed&" +
            "name=!null&" +
            "poster.url=!null&" +
            "token=C2YM9N6-ECGMM50-GFAA00K-Y7FSNBQ")
    suspend fun getMovies(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("limit") @IntRange(from = 1, to = 10) limit: Int = 10,
        @Query("year") year: String = "1860-2023",
        @Query("type") type: String = "movie"
    ) :Response<ResponsesApi>
}