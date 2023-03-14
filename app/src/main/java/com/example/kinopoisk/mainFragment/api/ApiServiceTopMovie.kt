package com.example.kinopoisk.mainFragment.api

import androidx.annotation.IntRange
import com.example.kinopoisk.mainFragment.models.ResponsesApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Year

interface ApiServiceTopMovie {
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
            "sortField=year&" +
            "sortType=-1&" +
            "top250=%21null&" +
            "status=completed&" +
            "name=!null&" +
            "poster.url=!null&" +
            "token=C2YM9N6-ECGMM50-GFAA00K-Y7FSNBQ")
    suspend fun getNewMovies(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("limit") @IntRange(from = 1, to = 10) limit: Int = 10,
        @Query("year") year: String = "1860-2023"
    ) :Response<ResponsesApi>
}