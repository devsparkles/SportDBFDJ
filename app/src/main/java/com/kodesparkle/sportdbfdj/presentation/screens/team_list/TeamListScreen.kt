package com.kodesparkle.sportdbfdj.presentation.screens.team_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodesparkle.sportdbfdj.presentation.screens.team_list.components.TeamComponent


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeamListScreen(
    viewModel: TeamListViewModel = hiltViewModel()
) {
    val teams by viewModel.teamItems.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.1f))
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            teams?.let {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp)
                ) {
                    items(it) { team ->
                        TeamComponent(team)
                    }
                }
            }

        }
    }
}
