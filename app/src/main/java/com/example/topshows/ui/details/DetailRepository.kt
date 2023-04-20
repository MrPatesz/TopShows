package com.example.topshows.ui.details

import com.example.topshows.persistence.TopShowDao
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val topShowDao: TopShowDao
) {
}