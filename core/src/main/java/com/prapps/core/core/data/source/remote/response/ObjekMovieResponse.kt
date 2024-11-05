package com.prapps.core.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ObjekMovieResponse(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("release_date")
    val release_date: String? = null,

    @field:SerializedName("popularity")
    val popularity: String? = null,

    @field:SerializedName("vote_average")
    val vote_average: Double? = null,

)
