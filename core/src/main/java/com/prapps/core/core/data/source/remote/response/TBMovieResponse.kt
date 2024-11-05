package com.prapps.core.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TBMovieResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val places: List<ObjekMovieResponse>,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)