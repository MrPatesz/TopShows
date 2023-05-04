package com.example.topshows.mock

import com.example.topshows.model.TopShowsResponse
import com.example.topshows.model.details.ShowDetails
import com.example.topshows.network.ShowService
import retrofit2.Call

class MockShowService : ShowService {
    override fun getTopShows(apiKey: String): Call<TopShowsResponse> {
        TODO("Not yet implemented")
    }

    override fun getShow(apiKey: String, tvId: Int): Call<ShowDetails> {
        TODO("Not yet implemented")
    }

    override fun deleteShow(tvId: Int): Call<Void> {
        TODO("Not yet implemented")
    }

    override fun postShow(data: ShowDetails): Call<ShowDetails> {
        TODO("Not yet implemented")
    }

    override fun putShow(tvId: Int, data: ShowDetails): Call<ShowDetails> {
        TODO("Not yet implemented")
    }
}