package com.example.topshows.ui.shows

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.topshows.model.TopShow
import com.example.topshows.ui.main.MainViewModel

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
            TopShowsList(modifier, topShows, selectShow)
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

// TODO separate file, use this on every screen
@Composable
private fun ShowsTopAppBar(goToAboutScreen: () -> Unit = {}) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .statusBarsPadding()
            .height(58.dp)
            .fillMaxWidth(),
        /*navigationIcon = IconButton(onClick = {  }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }*/
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (title, about) = createRefs()
            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .padding(8.dp, top = 16.dp),
                text = "Top Shows",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = { goToAboutScreen() },
                modifier = Modifier
                    .constrainAs(about) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .padding(8.dp)) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    tint = Color.White,
                    contentDescription = null,
                )
            }
        }
    }
}
