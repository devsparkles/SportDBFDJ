package com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodesparkle.sportdbfdj.R
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.LeaguesViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LeagueScreen(
    viewModel: LeaguesViewModel = hiltViewModel(),
    onLeagueClicked: (LeagueItem) -> Unit
) {
    val loading by viewModel.loading.observeAsState()
    val leagues by viewModel.leagueItems.observeAsState()

    Scaffold(topBar = { SearchBar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (loading != null && loading == true) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(30.dp),
                        color = Color.Blue,
                        strokeWidth = 3.dp
                    )
                } else {

                    LazyColumn(
                        contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val list = List(100) { "Text $it" }
                        items(count = list.size) {
                            Text(
                                list[it],
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
//                    LeagueList(
//                        leagues = leagues,
//                        onLeagueClicked = {
//                            onLeagueClicked(it)
//                        }
//                    )
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        // Talkback focus order sorts based on x and y position before considering z-index. The
        // extra Box with semantics and fillMaxWidth is a workaround to get the search bar to focus
        // before the content.
        Box(
            Modifier
                .semantics { isTraversalGroup = true }
                .zIndex(1f)
                .fillMaxWidth()) {
            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text("Hinted search text") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(4) { idx ->
                        val resultText = "Suggestion $idx"
                        ListItem(
                            headlineContent = { Text(resultText) },
                            supportingContent = { Text("Additional info") },
                            leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                            modifier = Modifier.clickable {
                                text = resultText
                                active = false
                            }
                        )
                    }
                }
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
