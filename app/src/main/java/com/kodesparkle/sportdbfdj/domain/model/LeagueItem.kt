package com.kodesparkle.sportdbfdj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LeagueItem(
    val idLeague: String = "",
    val strLeague: String = "",
    val strSport: String = "",
    val strLeagueAlternate: String = "",
) : Parcelable {
    override fun toString(): String {
        return "LeagueItem(idLeague='$idLeague', strLeague='$strLeague', strSport='$strSport', strLeagueAlternate='$strLeagueAlternate')"
    }
}
