package com.example.topshows.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.topshows.model.TopShow

@Database(entities = [TopShow::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topShowDao(): TopShowDao
}