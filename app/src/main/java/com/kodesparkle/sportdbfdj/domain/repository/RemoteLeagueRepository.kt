package com.kodesparkle.sportdbfdj.domain.repository

import com.kodesparkle.sportdbfdj.domain.model.LeagueResultItem
import com.kodesparkle.sportdbfdj.utils.resource.Resource

interface RemoteLeagueRepository {

    suspend fun getLeagues(): Resource<LeagueResultItem>
}
