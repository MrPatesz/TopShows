package com.example.topshows.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ShowsTopAppBar(
    goToAboutScreen: () -> Unit = {},
    pressOnBack: () -> Unit = {},
    hasBackArrow: Boolean = false,
    hasInfoButton: Boolean = true,
) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .statusBarsPadding()
            .height(58.dp)
            .fillMaxWidth(),
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (title, about) = createRefs()
            Row(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            ) {
                if(hasBackArrow) {
                    IconButton(
                        onClick = { pressOnBack() },
                        modifier = Modifier.padding(top = 6.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(8.dp, top = 16.dp),
                    text = "Top Shows",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if(hasInfoButton) {
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
}
