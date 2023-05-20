package com.example.topshows.ui.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.topshows.ui.about.About
import com.example.topshows.ui.details.ShowDetails
import com.example.topshows.ui.shows.TopShows

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            TopShows(
                viewModel = hiltViewModel(),
                selectShow = {
                    navController.navigate("${NavScreen.ShowDetails.route}/$it")
                },
                goToAboutScreen = {
                    navController.navigate(NavScreen.About.route)
                }
            )
        }
        composable(
            route = NavScreen.ShowDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.ShowDetails.argument0) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val showId =
                backStackEntry.arguments?.getInt(NavScreen.ShowDetails.argument0) ?: return@composable

            Text(text = "showId")
            ShowDetails(
                showId = showId,
                viewModel = hiltViewModel(),
                pressOnBack = {navController.navigateUp()},
                goToAboutScreen = { navController.navigate(NavScreen.About.route) }
            )
        }
        composable(NavScreen.About.route) {
            About(pressOnBack = {navController.navigateUp()})
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object About : NavScreen("About")

    object ShowDetails : NavScreen("ShowDetails") {

        const val routeWithArgument: String = "ShowDetails/{showId}"

        const val argument0: String = "showId"
    }
}