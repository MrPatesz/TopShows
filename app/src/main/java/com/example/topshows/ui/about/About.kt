package com.example.topshows.ui.about

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier

@Composable
fun About(pressOnBack: () -> Unit = {}) {
    Text(text="About Screen", modifier = Modifier.clickable { pressOnBack() })
}