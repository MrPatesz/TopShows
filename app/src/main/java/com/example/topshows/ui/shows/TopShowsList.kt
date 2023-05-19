package com.example.topshows.ui.shows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.topshows.model.TopShow
import com.example.topshows.utils.NetworkImage

@Composable
fun TopShowsList(
    modifier: Modifier = Modifier,
    topShows: List<TopShow>,
    selectShow: (Int) -> Unit = {},
) {
    val listState = rememberLazyListState()
    Column(modifier) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                items = topShows,
                key = { it.id },
                itemContent = { topShow ->
                    TopShowItem(
                        topShow = topShow,
                        selectShow = selectShow
                    )
                }
            )
        }
    }
}

@Composable
private fun TopShowItem(
    modifier: Modifier = Modifier,
    topShow: TopShow,
    selectShow: (Int) -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(
                onClick = { selectShow(topShow.id) }
            ),
        color = Color.LightGray,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(8.dp)
        ) {
            val (image, title, rating) = createRefs()

            topShow.poster_path?.let {
                NetworkImage(
                    modifier = Modifier
                        .constrainAs(image) {
                            centerVerticallyTo(parent)
                            end.linkTo(title.start)
                        }
                        .height(64.dp)
                        .aspectRatio(0.67f)
                        .clip(RoundedCornerShape(4.dp)),
                    url = it
                )
            }

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(image.end)
                    }
                    .padding(horizontal = 12.dp),
                text = "${topShow.name} (${topShow.first_air_date.substring(0,4)})",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier
                    .constrainAs(rating) {
                        start.linkTo(image.end)
                        top.linkTo(title.bottom)
                    }
                    .padding(start = 12.dp, top = 4.dp),
                text = "⭐${topShow.vote_average} (${topShow.vote_count})",
            )
        }
    }
}
