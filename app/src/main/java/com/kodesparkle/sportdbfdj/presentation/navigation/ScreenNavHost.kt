package com.kodesparkle.sportdbfdj.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.components.LeagueScreen

@Composable
fun ScreenNavHost(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        // home screen is the first destination
        composable(
            route = Screen.Home.route
        ) {
            LeagueScreen(
                onLeagueClicked = { league ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = Screen.Detail.backStackEntryStory,
                        value = league
                    )
                    navController.navigateSingleTopTo(Screen.Detail.route)
                }
            )
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
