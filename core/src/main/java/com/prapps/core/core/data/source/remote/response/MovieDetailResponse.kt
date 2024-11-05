package com.prapps.core.core.data.source.remote.response

data class MovieDetailResponse(

    val id: Int,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val backdrop_path: String?,
    val tagline: String?,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)

