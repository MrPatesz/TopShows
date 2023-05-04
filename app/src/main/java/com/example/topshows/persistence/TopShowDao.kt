package com.example.topshows.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.topshows.model.TopShow

@Dao
interface TopShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopShows(topShows: List<TopShow>)

    @Query("SELECT * FROM TopShow")
    suspend fun getTopShows(): List<TopShow>

    @Query("SELECT * FROM TopShow WHERE id = :id_")
    suspend fun getTopShow(id_: Int): TopShow?

    @Insert
    fun insertTopShow(topShow: TopShow)

    @Update
    fun updateTopShow(topShow: TopShow)

    @Delete
    fun deleteTopShow(topShow: TopShow)

    @Query("DELETE FROM TopShow")
    fun deleteAllTopShows()
}