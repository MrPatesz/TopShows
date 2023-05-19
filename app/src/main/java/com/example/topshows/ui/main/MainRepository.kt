package com.example.topshows.ui.main

import androidx.annotation.WorkerThread
import com.example.topshows.model.TopShow
import com.example.topshows.network.NetworkConfig
import com.example.topshows.network.ShowService
import com.example.topshows.persistence.TopShowDao
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val showService: ShowService,
    private val topShowDao: TopShowDao
) {
    @WorkerThread
    fun loadTopShows(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
    ) = flow {
        val topShows: List<TopShow> = topShowDao.getTopShows()
        if (true) { // TODO topShows.isEmpty()) {
            showService.getTopShows(NetworkConfig.API_KEY)
                .suspendOnSuccess {
                    val resultTopShows = data.results.asList()
                    topShowDao.insertTopShows(resultTopShows)
                    emit(resultTopShows)
                }
        } else {
            emit(topShows)
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}