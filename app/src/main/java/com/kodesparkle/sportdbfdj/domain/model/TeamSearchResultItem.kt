package com.kodesparkle.sportdbfdj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TeamSearchResultItem(
    val teams: List<TeamItem>
) : Parcelable
