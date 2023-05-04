package com.example.topshows.persistence

import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class TopShowDaoTest : LocalDatabase() {
    private lateinit var topShowDao: TopShowDao

    @Before
    fun init() {
        topShowDao = db.topShowDao()
    }

    // TODO testing
}