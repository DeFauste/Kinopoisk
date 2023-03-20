package com.example.kinopoisk.mainFragment.model.search

data class ResponsesSearch(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)