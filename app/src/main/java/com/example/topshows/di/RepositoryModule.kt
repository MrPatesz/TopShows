package com.example.topshows.di

import com.example.topshows.persistence.TopShowDao
import com.example.topshows.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        // topShowService: ShowService,
        topShowDao: TopShowDao
    ): MainRepository {
        return MainRepository(/*topShowService, topShowDao*/)
    }
}