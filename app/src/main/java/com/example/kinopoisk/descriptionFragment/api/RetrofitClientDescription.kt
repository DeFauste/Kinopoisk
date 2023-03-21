package com.example.kinopoisk.descriptionFragment.api

import com.example.kinopoisk.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientDescription {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiDescriptionMovie: ApiServiceDescription by lazy {
        retrofit
            .create(ApiServiceDescription::class.java)
    }
    val apiPersonsMovie: ApiServicePerson by lazy {
        retrofit
            .create(ApiServicePerson::class.java)
    }
}