package com.example.topshows.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {
    private val showIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

    val showDetailsFlow = showIdSharedFlow.flatMapLatest {
        detailRepository.getShowById(it)
    }

    fun loadShowById(id: Int) = showIdSharedFlow.tryEmit(id)
}