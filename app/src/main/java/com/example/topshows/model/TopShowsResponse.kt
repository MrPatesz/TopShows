package com.example.topshows.model

data class TopShowsResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: Array<TopShow>,
)