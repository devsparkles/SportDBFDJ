package com.kodesparkle.sportdbfdj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TeamItem(
    val id: String,
    val name: String,
    val imageUrl: String
) : Parcelable
