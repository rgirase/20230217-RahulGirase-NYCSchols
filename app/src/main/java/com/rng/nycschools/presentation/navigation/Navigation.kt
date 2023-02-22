package com.rng.nycschools.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rng.nycschools.presentation.SplashScreen
import com.rng.nycschools.presentation.school_Info.SchoolScoresScreen
import com.rng.nycschools.presentation.school_listing.SchoolListHomeScreen

/**
 * Navigation Component to handle navigation in the App
 * Declare App Compose Screens here
 */
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.SchoolListScreen.route) {
            SchoolListHomeScreen(navController = navController)
        }

        composable(
            route = Screen.SchoolScoresScreen.route + "/{schoolID}",
            arguments = listOf(navArgument("schoolID") {
                type = NavType.StringType
                defaultValue = "0"
                nullable
            })
        ) { entry ->
            entry.arguments?.getString("schoolID")?.let {
                SchoolScoresScreen(
                    schoolId = it, navController = navController
                )
            }

        }


    }

}