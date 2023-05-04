package com.example.topshows.ui.main

import com.example.topshows.network.ShowService
import com.example.topshows.persistence.TopShowDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val showService: ShowService,
    private val topShowDao: TopShowDao
)