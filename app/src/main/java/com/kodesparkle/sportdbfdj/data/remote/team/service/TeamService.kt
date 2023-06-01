package com.kodesparkle.sportdbfdj.data.remote.team.service

import com.kodesparkle.sportdbfdj.data.remote.team.dto.TeamSearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamService {

    @GET("/api/v1/json/50130162/search_all_teams.php")
    suspend fun searchTeamsByLeague(@Query("l") league: String): TeamSearchResultDto?
}
