package com.kodesparkle.sportdbfdj.presentation.screens.team_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodesparkle.sportdbfdj.di.annotations.IoDispatcher
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.domain.usecases.GetLeaguesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class TeamSearchViewModel @Inject constructor(
    val getLeaguesUseCase: GetLeaguesUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val _leagueItems: MutableLiveData<MutableList<LeagueItem>> =
        MutableLiveData(mutableListOf<LeagueItem>())
    var leagueItems: LiveData<MutableList<LeagueItem>> = _leagueItems

    val _loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading


    init {
        retrieveTeams()
    }

    private fun retrieveTeams() {
        viewModelScope.launch(ioDispatcher) {
            _loading.postValue(true)
            val leagues = getLeaguesUseCase()
            _leagueItems.postValue(leagues)
            _loading.postValue(false)
        }
    }
}
