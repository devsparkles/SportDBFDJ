package com.kodesparkle.sportdbfdj.data.remote.league.service

import com.kodesparkle.sportdbfdj.data.remote.league.dto.LeagueResultsDto
import retrofit2.http.GET

interface LeagueService {

    @GET("/api/v1/json/50130162/all_leagues.php")
    suspend fun getAllLeagues(): LeagueResultsDto?

}
