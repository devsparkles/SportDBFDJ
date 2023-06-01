package com.kodesparkle.sportdbfdj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueResultItem(
    val leagues: List<LeagueItem>
) : Parcelable
