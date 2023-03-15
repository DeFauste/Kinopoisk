package com.example.kinopoisk.descriptionFragment.models.modelForDescription

data class ResponsesDescription(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)