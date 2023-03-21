package com.example.kinopoisk.descriptionFragment.api

import com.example.kinopoisk.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientDescription {
    val apiDescriptionMovie: ApiServiceDescription by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceDescription::class.java)
    }
    val apiPersonsMovie: ApiServicePerson by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServicePerson::class.java)
    }
}