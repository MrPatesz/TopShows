package com.example.topshows.model

data class TopShow(
    val id: Int,
    val vote_count: Int,
    val popularity: Float,
    val vote_average: Float,
    val overview: String,
    val first_air_date: String,
    val original_language: String,
    val name: String,
    val original_name: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val origin_country: Array<String>,
    val genre_ids: Array<Int>,
)