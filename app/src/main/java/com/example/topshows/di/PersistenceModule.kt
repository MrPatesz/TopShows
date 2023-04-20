package com.example.topshows.di

import android.app.Application
import androidx.room.Room
import com.example.topshows.persistence.AppDatabase
import com.example.topshows.persistence.TopShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "top-shows-db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTopShowDao(appDatabase: AppDatabase): TopShowDao {
        return appDatabase.topShowDao()
    }
}