package com.example.kinopoisk.descriptionFragment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientDescription {
    val apiDescriptionMovie: ApiServiceDescription by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceDescription::class.java)
    }
    val apiPersonsMovie: ApiServicePerson by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServicePerson::class.java)
    }
}