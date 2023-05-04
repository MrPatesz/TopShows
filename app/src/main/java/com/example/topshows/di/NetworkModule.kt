package com.example.topshows.di

import com.example.topshows.network.NetworkConfig
import com.example.topshows.network.ShowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkConfig.ENDPOINT_ADDRESS)
            .build()
    }
    @Provides
    @Singleton
    fun provideShowService(retrofit: Retrofit): ShowService {
        return retrofit.create(ShowService::class.java)
    }
}