package com.kodesparkle.sportdbfdj.domain.usecases

import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.domain.repository.RemoteTeamRepository
import javax.inject.Inject

// respecting clean architecture all the logic exist
// in one place that contain no Android code and can be easily tested
class GetLeaguesUseCase @Inject constructor(private val remoteTeamRepository: RemoteTeamRepository) {

    suspend operator fun invoke(): MutableList<LeagueItem> {
        val leagues = remoteTeamRepository.getLeagues()
        if (leagues.isNotAnError()) {
            val l = leagues.value()?.leagues
            l?.let {
                return it.toMutableList()
            }
            return mutableListOf()
        } else {
            return mutableListOf()
        }
    }
}
