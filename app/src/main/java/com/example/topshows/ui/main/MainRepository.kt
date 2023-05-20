package com.example.topshows.ui.main

import androidx.annotation.WorkerThread
import com.example.topshows.model.TopShow
import com.example.topshows.network.NetworkConfig
import com.example.topshows.network.ShowService
import com.example.topshows.persistence.TopShowDao
import com.skydoves.sandwich.suspendOnFailure
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
        showService.getTopShows(NetworkConfig.API_KEY)
            .suspendOnSuccess {
                val resultTopShows = data.results.asList()
                topShowDao.insertTopShows(resultTopShows)
                emit(resultTopShows)
            }
            .suspendOnFailure {
                val topShows: List<TopShow> = topShowDao.getTopShows()
                emit(topShows.sortedByDescending { it.vote_average })
            }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}