package com.kodesparkle.sportdbfdj.data.mapper

import com.kodesparkle.sportdbfdj.data.remote.team.dto.TeamDto
import com.kodesparkle.sportdbfdj.data.remote.team.dto.TeamSearchResultDto
import com.kodesparkle.sportdbfdj.domain.model.TeamItem
import com.kodesparkle.sportdbfdj.domain.model.TeamSearchResultItem
import com.kodesparkle.sportdbfdj.utils.resource.Resource

fun Resource<TeamSearchResultDto?>.toDomain(): Resource<TeamSearchResultItem> {
    return when (this) {
        is Resource.Success -> Resource.Success(
            this.value()?.toDomain() ?: TeamSearchResultItem(
                teams = mutableListOf()
            )
        )

        is Resource.SuccessWithoutContent -> Resource.SuccessWithoutContent()
        is Resource.Error -> Resource.Error(this.error())
        is Resource.Loading -> Resource.Loading()
    }
}

fun TeamDto.toDomain(): TeamItem {
    return TeamItem(
        id = this.idTeam,
        name = this.strTeam,
        imageUrl = this.strTeamBadge
    )
}

fun List<TeamDto>?.toTeamDomain(): MutableList<TeamItem> {
    val result: MutableList<TeamItem> = mutableListOf()
    this?.forEach { result.add(it.toDomain()) }
    return result
}


fun TeamSearchResultDto.toDomain(): TeamSearchResultItem {
    return TeamSearchResultItem(
        teams = this.teams.toTeamDomain()
    )
}

