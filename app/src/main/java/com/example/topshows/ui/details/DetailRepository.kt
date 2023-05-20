package com.example.topshows.ui.details

import androidx.annotation.WorkerThread
import com.example.topshows.network.NetworkConfig
import com.example.topshows.network.ShowService
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val showService: ShowService
) {
    @WorkerThread
    fun getShowById(id: Int) = flow {
        showService.getShow(id, NetworkConfig.API_KEY)
            .suspendOnSuccess {
                emit(data)
            }
    }.flowOn(Dispatchers.IO)
}