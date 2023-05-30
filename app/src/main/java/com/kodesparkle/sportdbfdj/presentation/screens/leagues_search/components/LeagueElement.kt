package com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem

@Composable
fun LeagueElement(
    leagueItem: LeagueItem,
    onLeagueClicked: (LeagueItem) -> Unit
){
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = leagueItem.strLeague ?: "",
        fontSize = 15.sp,
        fontWeight = FontWeight.Black,
        color = Color.Black
    )
}
