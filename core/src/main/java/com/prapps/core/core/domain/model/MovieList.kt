package com.prapps.core.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
    val id: Int?,
    val overview: String?,
    val title: String?,
    val poster_path: String?,
    val release_date: String?,
    val popularity: String?,
    val vote_average: Double?,
) : Parcelable