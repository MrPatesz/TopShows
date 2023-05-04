package com.example.topshows.mock

import com.example.topshows.network.ShowService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockNetworkModule {
    @Provides
    @Singleton
    fun provideShowService(): ShowService = MockShowService()
}