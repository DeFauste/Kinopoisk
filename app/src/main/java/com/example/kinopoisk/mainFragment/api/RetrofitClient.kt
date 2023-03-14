package com.example.kinopoisk.mainFragment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: ApiServiceNewMovie by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceNewMovie::class.java)
    }
}