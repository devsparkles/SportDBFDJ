package com.kodesparkle.sportdbfdj.presentation.screens.leagues_search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.components.SearchBarComponent

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LeagueScreen(
    viewModel: LeaguesViewModel = hiltViewModel(),
    onLeagueClicked: (LeagueItem) -> Unit
) {
    val loading by viewModel.isSearching.collectAsState(initial = false)
    val leagues by viewModel.leagues.collectAsState(initial = mutableListOf())

    Scaffold(topBar = {
        SearchBarComponent(
            leagues = leagues,
            loading = loading,
            onLeagueClicked = onLeagueClicked,
            searchLeague = viewModel::searchLeague
        )
    }, content = { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding))
    }
    )

}
