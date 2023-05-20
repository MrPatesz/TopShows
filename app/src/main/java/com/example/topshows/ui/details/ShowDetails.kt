package com.example.topshows.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.topshows.model.details.Season
import com.example.topshows.model.details.ShowDetails
import com.example.topshows.utils.NetworkImage
import com.example.topshows.utils.ShowsTopAppBar

@Composable
fun ShowDetails(
    showId: Int,
    viewModel: DetailViewModel,
    pressOnBack: () -> Unit = {},
    goToAboutScreen: () -> Unit = {},
) {
    LaunchedEffect(key1 = showId) {
        viewModel.loadShowById(showId)
    }

    Scaffold(
        backgroundColor = Color.Gray,
        topBar = { ShowsTopAppBar(
            goToAboutScreen = { goToAboutScreen() },
            hasBackArrow = true,
            pressOnBack = { pressOnBack() }
        ) },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        val details: ShowDetails? by viewModel.showDetailsFlow.collectAsState(initial = null)
        details?.let { showDetails ->
            ShowDetailsBody(modifier, showDetails)
        }
    }
}

@Composable
private fun ShowDetailsBody(
    modifier: Modifier = Modifier,
    showDetails: ShowDetails,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(Color.Gray)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
            val (details, backdrop, overviewTitle, overview, seasonsTitle, seasons) = createRefs()

            Row(modifier = Modifier
                .constrainAs(details) {
                    top.linkTo(parent.top)
                }
            ) {
                showDetails.poster_path?.let {
                    NetworkImage(
                        url = it,
                        modifier = Modifier
                            .height(160.dp)
                            .aspectRatio(0.67f)
                            .padding(8.dp),
                        circularRevealEnabled = true,
                    )
                }
                Column {
                    Text(
                        text = showDetails.name,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "⭐${showDetails.vote_average} (${showDetails.vote_count})",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(8.dp)
                    )
                    val dateParts1 = showDetails.first_air_date.split("-")
                    val dateString1 = "${dateParts1[1]}/${dateParts1[2]}/${dateParts1[0]}"
                    val dateParts2 = showDetails.last_air_date.split("-")
                    val dateString2 = "${dateParts2[1]}/${dateParts2[2]}/${dateParts2[0]}"
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = "${dateString1} - ${dateString2}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = "${showDetails.number_of_episodes} episodes in ${showDetails.number_of_seasons} season${if (showDetails.number_of_seasons == 1) "" else "s"}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Text(
                text = "Overview",
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(overviewTitle) {
                        top.linkTo(details.bottom)
                    }
            )

            Text(
                text = showDetails.overview,
                style = MaterialTheme.typography.body1,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(overview) {
                        top.linkTo(overviewTitle.bottom)
                    }
                    .padding(8.dp, bottom = 4.dp)
            )

            showDetails.backdrop_path?.let {
                NetworkImage(
                    url = it,
                    modifier = Modifier
                        .constrainAs(backdrop) {
                            top.linkTo(overview.bottom)
                        }
                        .fillMaxWidth()
                        .padding(8.dp)
                        .aspectRatio(1.78f),
                    circularRevealEnabled = true,
                    size = "w780"
                )
            }

            Text(
                text = "Seasons",
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(seasonsTitle) {
                        top.linkTo(backdrop.bottom)
                    }
            )

            SeasonsList(
                seasons = showDetails.seasons.toList(),
                modifier = Modifier
                .constrainAs(seasons) {
                    top.linkTo(seasonsTitle.bottom)
                    bottom.linkTo(parent.bottom)
                }
                    .padding(bottom=30.dp)
            )
        }
    }
}

@Composable
fun SeasonsList(
    modifier: Modifier = Modifier,
    seasons: List<Season>,
) {
    Column(modifier = modifier) {
        seasons.forEach{ season ->
            SeasonItem(season = season)
        }
    }
}

@Composable
private fun SeasonItem(
    modifier: Modifier = Modifier,
    season: Season,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        color = Color.LightGray,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(8.dp)
        ) {
            val (title, numberOfEpisodes) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                    }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                text = "${season.season_number}. ${season.name}",
            )

            Text(
                modifier = Modifier
                    .constrainAs(numberOfEpisodes) {
                        end.linkTo(parent.end)
                    }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                text = "${season.episode_count} episodes",
            )
        }
    }
}
