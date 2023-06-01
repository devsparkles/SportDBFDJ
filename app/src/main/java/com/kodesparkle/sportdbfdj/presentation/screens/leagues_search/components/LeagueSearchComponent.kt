package com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem


@Composable
fun LeagueSearchComponent(
    leagues: List<LeagueItem>,
    loading: Boolean,
    onLeagueClicked: (LeagueItem) -> Unit,
    searchLeague: (String) -> Unit
) {
    Scaffold(topBar = {
        SearchBarComponent(
            leagues = leagues,
            loading = loading,
            onLeagueClicked = onLeagueClicked,
            searchLeague = searchLeague
        )
    }, content = { contentPadding ->
        Box(modifier = androidx.compose.ui.Modifier.padding(contentPadding))
    })
}
