package com.example.kinopoisk.mainFragment.api

import com.example.kinopoisk.mainFragment.models.ResponsesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: ApiServiceMainFragment by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceMainFragment::class.java)
    }
}