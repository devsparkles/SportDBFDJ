package com.kodesparkle.sportdbfdj.presentation.screens.team_search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodesparkle.sportdbfdj.presentation.screens.team_search.TeamSearchViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeamScreen(
    viewModel: TeamSearchViewModel = hiltViewModel()
) {

    val loading by viewModel.loading.observeAsState()
    val posts by viewModel.leagueItems.observeAsState()

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

        }
    }
}
