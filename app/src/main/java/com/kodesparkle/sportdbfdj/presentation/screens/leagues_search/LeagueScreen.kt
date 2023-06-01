package com.kodesparkle.sportdbfdj.presentation.screens.leagues_search

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.components.LeagueSearchComponent

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LeagueScreen(
    viewModel: LeaguesViewModel = hiltViewModel(),
    onLeagueClicked: (LeagueItem) -> Unit
) {
    val loading by viewModel.isSearching.collectAsState(initial = false)
    val leagues by viewModel.leagues.collectAsState(initial = mutableListOf())

    LeagueSearchComponent(
        leagues = leagues,
        loading = loading,
        onLeagueClicked = onLeagueClicked,
        searchLeague = viewModel::searchLeague
    )
}
