package com.kodesparkle.sportdbfdj.data.remote.league.repository

import com.kodesparkle.sportdbfdj.data.mapper.toDomain
import com.kodesparkle.sportdbfdj.data.remote.league.dto.LeagueResultsDto
import com.kodesparkle.sportdbfdj.data.remote.league.service.LeagueService
import com.kodesparkle.sportdbfdj.domain.model.LeagueResultItem
import com.kodesparkle.sportdbfdj.domain.repository.RemoteLeagueRepository
import com.kodesparkle.sportdbfdj.utils.resource.Resource

class RemoteLeagueRepositoryImpl(private val leagueService: LeagueService) :
    RemoteLeagueRepository {

    // since the backend do not have a working cache we will use a local cache for that demo
    var cache: LeagueResultsDto? = null

    override suspend fun getLeagues(): Resource<LeagueResultItem> {
        if (cache == null) {
            cache = leagueService.getAllLeagues()
        }
        try {
            val response = Resource.of { cache }
            // if we wanted to call other services to combine their information we do that here
            // we could also do something like response.isAnError()
            // and call another service that could talk to another endpoint on another server
            // but for now we will just deliver the data
            return response.toDomain()
        } catch (e: Exception) {
            // there is already a resource error handling inside the Resource class so that code appears to not be useful
            // it is here in case  some code done after the first call is done directly with the suspend
            // and fail that way we have a wrapper over all backend call to handle that case
            return Resource.Error(e)
        }
    }


}
