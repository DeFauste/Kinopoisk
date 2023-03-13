package com.example.kinopoisk.mainFragment.models

data class ResponsesApi(
    val docs: List<Movies>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)