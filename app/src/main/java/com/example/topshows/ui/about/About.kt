package com.example.topshows.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.topshows.R
import com.example.topshows.utils.ShowsTopAppBar

@Composable
fun About(pressOnBack: () -> Unit = {}) {
    Scaffold(
        backgroundColor = Color.Gray,
        topBar = { ShowsTopAppBar(
            hasInfoButton = false,
            hasBackArrow = true,
            pressOnBack = { pressOnBack() }
        ) },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .size(240.dp)
                    .padding(32.dp),
            )

            Text("TopShows")
            Text("by Patrik Ángyán")
            Text(
                "Top rated TV shows on TMDb.",
                modifier = Modifier.padding(top=8.dp)
            )

            Image(
                imageVector = ImageVector.vectorResource(R.drawable.tmdb_logo),
                contentDescription = "TMDb logo",
                modifier = Modifier
                    .size(160.dp)
                    .padding(top = 32.dp, bottom = 16.dp),
            )
            Text("This product uses the TMDb API but is")
            Text("not endorsed or certified by TMDb.")
        }
    }
}