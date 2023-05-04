package com.example.topshows.model.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShowDetails (
    @PrimaryKey val id: Int,
    val backdrop_path: String?,
    val poster_path: String?,
    val created_by: Array<CreatedBy>,
    val genres: Array<Genre>,
    val networks: Array<Network>,
    val production_companies: Array<ProductionCompany>,
    val production_countries: Array<ProductionCountry>,
    val seasons: Array<Season>,
    val spoken_languages: Array<SpokenLanguage>,
    val episode_run_time: Array<Int>,
    val first_air_date: String,
    val homepage: String,
    val last_air_date: String,
    val name: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val status: String,
    val tagline: String,
    val type: String,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val vote_count: Int,
    val popularity: Float,
    val vote_average: Float,
    val languages: Array<String>,
    val origin_country: Array<String>,
    val in_production: Boolean,
    val last_episode_to_air: LastEpisodeToAir,
    // val next_episode_to_air: Null,
)