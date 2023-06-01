package com.kodesparkle.sportdbfdj.presentation.screens.team_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodesparkle.sportdbfdj.domain.model.TeamItem

@Composable
fun TeamListComponent(teams: MutableList<TeamItem>) {
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
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp)
            ) {
                items(teams) { team ->
                    TeamComponent(team)
                }
            }
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640, unit=dp,dpi=480")
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
@Composable
fun TeamListComponentPreview() {
    val teams = mutableListOf<TeamItem>()
    teams.add(
        TeamItem(
            id = "133604",
            name = "Arsenal",
            imageUrl = "https://www.thesportsdb.com/images/media/team/badge/xtwxyt1421431860.png"
        )
    )
    teams.add(
        TeamItem(
            id = "133702",
            name = "Ajaccio",
            imageUrl = "https://www.thesportsdb.com/images/media/team/badge/qpxvwy1473505505.png"
        )
    )
    teams.add(
        TeamItem(
            id = "134709",
            name = "Angers",
            imageUrl = "https://www.thesportsdb.com/images/media/team/badge/ix6q4w1678808069.png"
        )
    )

    teams.add(
        TeamItem(
            id = "134713",
            name = "Clermont Foot",
            imageUrl = "https://www.thesportsdb.com/images/media/team/badge/wrytst1426871249.png"
        )
    )
    TeamListComponent(teams)
}
