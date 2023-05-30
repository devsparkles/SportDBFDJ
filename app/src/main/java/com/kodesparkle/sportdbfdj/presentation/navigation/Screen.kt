package com.kodesparkle.sportdbfdj.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Screen(val name: String, val route: String) {

    object Home : Screen(
        name = "Home", route = "home"
    )

    object Detail : Screen(
        name = "Detail",
        route = "detail"
    ){
        const val backStackEntryStory = "league"
    }


}
