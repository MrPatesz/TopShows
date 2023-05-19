package com.example.topshows.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopShow(
    @PrimaryKey val id: Int,
    val name: String,
    val first_air_date: String,
    val vote_average: Float,
    val vote_count: Int,
    val poster_path: String?,
)