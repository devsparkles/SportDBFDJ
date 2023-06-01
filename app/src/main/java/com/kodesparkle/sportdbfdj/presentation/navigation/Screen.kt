package com.kodesparkle.sportdbfdj.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Screen(val name: String, val route: String) {

    object Home : Screen(
        name = "Home", route = "home"
    )

    object TeamList : Screen(
        name = "TeamList", route = "teamlist"
    ) {
        const val leagueNameArg = "leagueName"
        val routeWithArgs = "$route/{$leagueNameArg}"
        val arguments = listOf(
            navArgument(leagueNameArg) { type = NavType.StringType }
        )
    }


}
