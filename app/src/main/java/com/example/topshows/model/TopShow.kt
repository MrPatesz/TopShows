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
) {
    companion object {
        fun mock1(name: String = "True Detective") = TopShow(
            id = 46648,
            name = name,
            first_air_date = "2014-01-12",
            vote_average = 8.3f,
            vote_count = 2626,
            poster_path = "/aowr4xpLP5sRCL50TkuADomJ98T.jpg",
        )

        fun mock2() = TopShow(
            id = 40075,
            name = "Gravity Falls",
            first_air_date = "2012-06-15",
            vote_average = 8.5f,
            vote_count = 2359,
            poster_path = "/t9inzSLIttATX6RdnmDjL7T4WN7.jpg",
        )
    }
}
