package com.kodesparkle.sportdbfdj.presentation.screens.leagues_show.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodesparkle.sportdbfdj.R
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.presentation.screens.leagues_show.LeaguesViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LeagueScreen(
    viewModel: LeaguesViewModel = hiltViewModel(),
    onLeagueClicked: (LeagueItem) -> Unit
) {

    val loading by viewModel.loading.observeAsState()
    val leagues by viewModel.leagueItems.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray.copy(alpha = 0.5f))
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (loading != null && loading == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(30.dp),
                        color = Color.Blue,
                        strokeWidth = 3.dp
                    )
                }
            } else {
                LeagueList(
                    leagues = leagues,
                    onLeagueClicked = {
                        onLeagueClicked(it)
                    }
                )
            }
        }
    }
}

@Composable
fun LeagueList(
    leagues: MutableList<LeagueItem>?,
    onLeagueClicked: (LeagueItem) -> Unit
) {
    if (leagues.isNullOrEmpty()) {
        Text(
            text = stringResource(R.string.no_league_found),
        )
    } else {
        LazyColumn {
            items(leagues) { league ->
                LeagueElement(league) { l ->
                    onLeagueClicked(l)
                }
            }
        }
    }
}

