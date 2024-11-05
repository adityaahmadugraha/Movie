package com.prapps.core.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieFavorite(
    val id: Int,
    val overview: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val popularity: String,
    val voteAverage: Double
) : Parcelable
