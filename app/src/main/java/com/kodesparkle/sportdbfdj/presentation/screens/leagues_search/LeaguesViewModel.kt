package com.kodesparkle.sportdbfdj.presentation.screens.leagues_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodesparkle.sportdbfdj.di.annotations.IoDispatcher
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.domain.usecases.SearchLeaguesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(
    val searchLeaguesUseCase: SearchLeaguesUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

//    private val _leagues: MutableLiveData<MutableList<LeagueItem>> =
//        MutableLiveData(mutableListOf())
//    var leagues: LiveData<MutableList<LeagueItem>> = _leagues

//    private val _isSearching: MutableLiveData<Boolean> = MutableLiveData(true)
//    val isSearching: LiveData<Boolean> = _isSearching

    private val _leagues = MutableStateFlow(mutableListOf<LeagueItem>())
    val leagues = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_leagues) { text, leagues ->
            if(text.isBlank()) {
                leagues
            } else {
                searchLeaguesUseCase(text)
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _leagues.value
        )


    fun searchLeague(query: String) {
        _searchText.value = query
    }
//    fun xsearchLeague(query: String) {
//        viewModelScope.launch(ioDispatcher) {
//            _isSearching.postValue(true)
//            val leagues = searchLeaguesUseCase(query)
//            _leagues.postValue(leagues)
//            _isSearching.postValue(false)
//        }
//    }
}
