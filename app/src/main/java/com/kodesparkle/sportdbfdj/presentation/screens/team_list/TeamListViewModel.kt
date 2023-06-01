package com.kodesparkle.sportdbfdj.presentation.screens.team_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodesparkle.sportdbfdj.di.annotations.IoDispatcher
import com.kodesparkle.sportdbfdj.domain.model.TeamItem
import com.kodesparkle.sportdbfdj.domain.usecases.SearchTeamByLeagueUseCase
import com.kodesparkle.sportdbfdj.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TeamListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val searchTeamByLeagueUseCase: SearchTeamByLeagueUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _teamItems: MutableLiveData<MutableList<TeamItem>> =
        MutableLiveData(mutableListOf<TeamItem>())
    var teamItems: LiveData<MutableList<TeamItem>> = _teamItems

    init {
        savedStateHandle.get<String>(Screen.TeamList.leagueNameArg)?.let { leagueName ->
            searchTeamByLeagueName(leagueName)
        }
    }

    fun searchTeamByLeagueName(leagueName: String) {
        viewModelScope.launch(ioDispatcher) {
            val teams = searchTeamByLeagueUseCase(leagueName)
            println("count of teams ${teams.size}")
            _teamItems.postValue(teams)
        }
    }
}
