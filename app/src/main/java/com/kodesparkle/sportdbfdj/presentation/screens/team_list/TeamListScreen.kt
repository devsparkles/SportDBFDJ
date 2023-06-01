package com.kodesparkle.sportdbfdj.presentation.screens.team_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodesparkle.sportdbfdj.presentation.screens.team_list.components.TeamListComponent


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeamListScreen(
    viewModel: TeamListViewModel = hiltViewModel()
) {
    val teams by viewModel.teamItems.observeAsState()
    TeamListComponent(teams = teams ?: mutableListOf())
}


