package com.prapps.core.core.data.source.remote.network

import com.prapps.core.core.data.source.remote.response.TBMovieResponse
import com.prapps.core.core.data.source.remote.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getList(
        @Query("api_key") key: String?
    ): TBMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("api_key") key: String?
    ): MovieDetailResponse
}