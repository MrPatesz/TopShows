package com.example.topshows.model.details

data class ShowDetails (
    val id: Int,
    val name: String,
    val vote_average: Float,
    val vote_count: Int,
    val first_air_date: String,
    val last_air_date: String,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val overview: String,
    val backdrop_path: String?,
    val poster_path: String?,
    val seasons: Array<Season>,
)