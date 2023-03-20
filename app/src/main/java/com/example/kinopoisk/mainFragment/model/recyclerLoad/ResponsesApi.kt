package com.example.kinopoisk.mainFragment.model.recyclerLoad

data class ResponsesApi(
    val docs: List<Movies>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)