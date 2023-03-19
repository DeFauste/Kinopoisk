package com.example.kinopoisk.descriptionFragment.models.modelsDescr

data class ResponsesDescr(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)