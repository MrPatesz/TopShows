package com.example.topshows.network

import com.example.topshows.model.TopShowsResponse
import com.example.topshows.model.details.ShowDetails
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowService {

    @GET("tv/top_rated")
    suspend fun getTopShows(@Query("api_key") apiKey: String): ApiResponse<TopShowsResponse>

    @GET("tv/{tv_id}")
    suspend fun getShow(
        @Query("api_key") apiKey: String,
        @Path("tv_id") tvId: Int
    ): ApiResponse<ShowDetails>

    @DELETE("tv/{tv_id}/delete")
    fun deleteShow(@Path("tv_id") tvId: Int): ApiResponse<Void>

    @POST("tv/post")
    fun postShow(@Body data: ShowDetails): ApiResponse<ShowDetails>

    @PUT("tv/{tv_id}/put")
    fun putShow(
        @Path("tv_id") tvId: Int,
        @Body data: ShowDetails
    ): ApiResponse<ShowDetails>
}