package com.example.topshows.mock

import com.example.topshows.model.TopShowsResponse
import com.example.topshows.model.details.ShowDetails
import com.example.topshows.network.ShowService
import com.skydoves.sandwich.ApiResponse

class MockShowService : ShowService {
    override suspend fun getTopShows(apiKey: String): ApiResponse<TopShowsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getShow(tvId: Int, apiKey: String): ApiResponse<ShowDetails> {
        TODO("Not yet implemented")
    }

    override fun deleteShow(tvId: Int): ApiResponse<Void> {
        TODO("Not yet implemented")
    }

    override fun postShow(data: ShowDetails): ApiResponse<ShowDetails> {
        TODO("Not yet implemented")
    }

    override fun putShow(tvId: Int, data: ShowDetails): ApiResponse<ShowDetails> {
        TODO("Not yet implemented")
    }
}