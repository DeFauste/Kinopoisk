package com.example.kinopoisk.mainFragment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val apiNewMovies: ApiServiceNewMovie by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceNewMovie::class.java)
    }
    val apiTopMovie: ApiServiceTopMovie by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceTopMovie::class.java)
    }
    val apiTypeMovie: ApiServiceTypeMovie by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceTypeMovie::class.java)
    }
    val apiDynamicSearch: ApiDynamicSearch by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiDynamicSearch::class.java)
    }
}