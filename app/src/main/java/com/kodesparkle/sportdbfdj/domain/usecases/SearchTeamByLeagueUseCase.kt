package com.kodesparkle.sportdbfdj.domain.usecases

import com.kodesparkle.sportdbfdj.domain.model.TeamItem
import com.kodesparkle.sportdbfdj.domain.repository.RemoteTeamRepository
import javax.inject.Inject


class SearchTeamByLeagueUseCase @Inject constructor(private val remoteTeamRepository: RemoteTeamRepository) {

    suspend operator fun invoke(query: String): MutableList<TeamItem> {
        // First get the league from the internet.
        // That request is using the cache, if the value do not change on the server
        // the cache will be used
        val leagues = remoteTeamRepository.searchTeamByLeagueName(query)
        if (leagues.isNotAnError()) {
            val l = leagues.value()?.teams
            l?.let {
                return it.toMutableList()
            }
            return mutableListOf()
        } else {
            return mutableListOf()
        }
    }
}
