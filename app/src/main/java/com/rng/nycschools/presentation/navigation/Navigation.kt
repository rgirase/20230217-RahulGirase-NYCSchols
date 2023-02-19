package com.rng.nycschools.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rng.nycschools.presentation.school_Info.SchoolScoresScreen
import com.rng.nycschools.presentation.school_listing.SchoolListsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SchoolListScreen.route) {
        composable(route = Screen.SchoolListScreen.route) {
            SchoolListsScreen(navController = navController)
        }

        composable(route = Screen.SchoolScoresScreen.route + "/{schoolID}",
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