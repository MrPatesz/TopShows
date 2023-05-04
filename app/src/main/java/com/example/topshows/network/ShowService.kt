package com.example.topshows.network

import com.example.topshows.model.TopShowsResponse
import com.example.topshows.model.details.ShowDetails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowService {

    @GET("tv/top_rated")
    fun getTopShows(@Query("api_key") apiKey: String): Call<TopShowsResponse>

    @GET("tv/{tv_id}")
    fun getShow(
        @Query("api_key") apiKey: String,
        @Path("tv_id") tvId: Int
    ): Call<ShowDetails>

    @DELETE("tv/{tv_id}/delete")
    fun deleteShow(@Path("tv_id") tvId: Int): Call<Void>

    @POST("tv/post")
    fun postShow(@Body data: ShowDetails): Call<ShowDetails>

    @PUT("tv/{tv_id}/put")
    fun putShow(
        @Path("tv_id") tvId: Int,
        @Body data: ShowDetails
    ): Call<ShowDetails>
}