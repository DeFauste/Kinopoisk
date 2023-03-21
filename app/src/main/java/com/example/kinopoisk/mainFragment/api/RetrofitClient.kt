package com.example.kinopoisk.mainFragment.api

import com.example.kinopoisk.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiNewMovies: ApiServiceNewMovie by lazy {
        retrofit
            .create(ApiServiceNewMovie::class.java)
    }
    val apiTopMovie: ApiServiceTopMovie by lazy {
        retrofit
            .create(ApiServiceTopMovie::class.java)
    }
    val apiTypeMovie: ApiServiceTypeMovie by lazy {
        retrofit
            .create(ApiServiceTypeMovie::class.java)
    }
    val apiDynamicSearch: ApiDynamicSearch by lazy {
        retrofit
            .create(ApiDynamicSearch::class.java)
    }
}