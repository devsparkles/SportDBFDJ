package com.kodesparkle.sportdbfdj.domain.repository

import com.kodesparkle.sportdbfdj.domain.model.LeagueResultItem
import com.kodesparkle.sportdbfdj.domain.model.TeamSearchResultItem
import com.kodesparkle.sportdbfdj.utils.resource.Resource

interface RemoteTeamRepository {

    suspend fun getLeagues(): Resource<LeagueResultItem?>

    suspend fun searchTeam(teamName: String): Resource<TeamSearchResultItem?>
}