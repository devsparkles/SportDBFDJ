package com.kodesparkle.sportdbfdj.data.mapper

import com.kodesparkle.sportdbfdj.data.remote.league.dto.LeagueDto
import com.kodesparkle.sportdbfdj.data.remote.league.dto.LeagueResultsDto
import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.domain.model.LeagueResultItem
import com.kodesparkle.sportdbfdj.utils.resource.Resource

fun Resource<LeagueResultsDto?>.toDomain(): Resource<LeagueResultItem?> {
    return when (this) {
        is Resource.Success -> Resource.Success(
            this.value()?.toDomain()
        )
        is Resource.SuccessWithoutContent -> Resource.SuccessWithoutContent()
        is Resource.Error -> Resource.Error(this.error())
        is Resource.Loading -> Resource.Loading()
    }
}

fun LeagueDto.toDomain(): LeagueItem {
    return LeagueItem(
        idLeague = this.idLeague ?: "",
        strLeague = this.strLeague ?: "",
        strSport = this.strSport ?: "",
        strLeagueAlternate = this.strLeagueAlternate ?: "",
    )
}

fun List<LeagueDto>?.toLeagueDomain(): MutableList<LeagueItem> {
    val result: MutableList<LeagueItem> = mutableListOf()
    this?.forEach { result.add(it.toDomain()) }
    return result
}


fun LeagueResultsDto.toDomain(): LeagueResultItem {
    return LeagueResultItem(
        leagues = this.leagues.toLeagueDomain()
    )
}

