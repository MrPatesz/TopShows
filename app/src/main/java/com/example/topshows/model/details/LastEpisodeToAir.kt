package com.example.topshows.model.details

data class LastEpisodeToAir(
    val air_date: String,
    val name: String,
    val overview: String,
    val production_code: String,
    val still_path: String?,
    val episode_number: Int,
    val id: Int,
    val season_number: Int,
    val vote_average: Float,
    val vote_count: Int,
)
