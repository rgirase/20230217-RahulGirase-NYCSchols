package com.rng.nycschools.presentation.navigation

//Navigation Screen List
sealed class Screen(val route: String) {
    object SchoolListScreen : Screen("school_listing_screen")
    object SchoolScoresScreen : Screen("school_score_screen")

    object SplashScreen : Screen("splash_screen")

    // function for handling multiple arguments
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}
