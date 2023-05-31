package com.kodesparkle.sportdbfdj.domain.usecases

import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.domain.model.LeagueResultItem
import com.kodesparkle.sportdbfdj.domain.repository.RemoteLeagueRepository
import com.kodesparkle.sportdbfdj.domain.repository.RemoteTeamRepository
import com.kodesparkle.sportdbfdj.utils.resource.Resource
import javax.inject.Inject

// respecting clean architecture all the logic exist
// in one place that contain no Android code and can be easily tested
class SearchLeaguesUseCase @Inject constructor(private val remoteLeagueRepository: RemoteLeagueRepository) {


    suspend operator fun invoke(query: String): MutableList<LeagueItem> {
        val leagues=  remoteLeagueRepository.getLeagues()
        if (leagues.isNotAnError()) {
            val l = leagues.value()?.leagues
            l?.let {
                val filteredList = it.filter { item ->
                    item.strLeague.contains(query, ignoreCase = true)
                }
                return filteredList.toMutableList()
            }
            return mutableListOf()
        } else {
            return mutableListOf()
        }
    }
}
