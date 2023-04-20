package com.example.topshows.network

import com.example.topshows.model.TopShowsResponse

interface ShowService {

    suspend fun fetchTopShows(): TopShowsResponse
}