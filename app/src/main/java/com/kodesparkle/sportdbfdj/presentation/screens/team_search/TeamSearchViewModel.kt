package com.kodesparkle.sportdbfdj.presentation.screens.team_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodesparkle.sportdbfdj.di.annotations.IoDispatcher
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.domain.model.TeamItem
import com.kodesparkle.sportdbfdj.domain.usecases.SearchLeaguesUseCase
import com.kodesparkle.sportdbfdj.domain.usecases.SearchTeamByLeagueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class TeamSearchViewModel @Inject constructor(
    val searchTeamByLeagueUseCase: SearchTeamByLeagueUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _teamItems: MutableLiveData<MutableList<TeamItem>> =
        MutableLiveData(mutableListOf<TeamItem>())
    var teamItems: LiveData<MutableList<TeamItem>> = _teamItems

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun searchTeamByLeagueName(leagueName: String) {
        viewModelScope.launch(ioDispatcher) {
            _loading.postValue(true)
            val teams = searchTeamByLeagueUseCase(leagueName)
            _teamItems.postValue(teams)
            _loading.postValue(false)
        }
    }
}
