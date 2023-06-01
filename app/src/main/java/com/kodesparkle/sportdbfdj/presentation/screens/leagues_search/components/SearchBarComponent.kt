package com.kodesparkle.sportdbfdj.presentation.screens.leagues_search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kodesparkle.sportdbfdj.R
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    leagues: List<LeagueItem> = emptyList(),
    loading: Boolean,
    onLeagueClicked: (LeagueItem) -> Unit,
    searchLeague: (String) -> Unit,

    ) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .semantics { isTraversalGroup = true }
                .zIndex(1f)
                .fillMaxWidth()) {
            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                query = text,
                onQueryChange = {
                    text = it
                    searchLeague(text)
                },
                onSearch = {
                    active = false
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text(stringResource(R.string.search_league)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { },
            ) {
                if (loading) {
                    CircularProgressIndicator()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(leagues) { idx ->
                            ListItem(
                                headlineContent = { Text(idx.strLeague) },
                                supportingContent = { Text(idx.strSport) },
                                leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                                modifier = Modifier.clickable {
                                    text = idx.strLeague
                                    active = false
                                    onLeagueClicked(idx)
                                }
                            )
                        }
                    }
                }
            }

        }

    }
}


