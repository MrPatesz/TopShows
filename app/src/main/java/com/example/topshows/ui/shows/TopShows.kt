package com.example.topshows.ui.shows

import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.topshows.model.TopShow
import com.example.topshows.ui.main.MainViewModel
import com.example.topshows.utils.ShowsTopAppBar

@Composable
fun TopShows(
    viewModel: MainViewModel,
    selectShow: (Int) -> Unit = {},
    goToAboutScreen: () -> Unit = {},
) {
    val topShows: List<TopShow> by viewModel.topShows.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading

    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            backgroundColor = Color.Gray,
            topBar = { ShowsTopAppBar(goToAboutScreen = { goToAboutScreen() }) },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            TopShowsList(modifier.padding(bottom=24.dp), topShows, selectShow)
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(progress) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}
