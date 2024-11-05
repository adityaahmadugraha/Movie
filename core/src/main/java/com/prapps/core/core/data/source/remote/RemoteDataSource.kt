package com.prapps.core.core.data.source.remote

import com.prapps.core.core.data.source.remote.network.ApiResponse
import com.prapps.core.core.data.source.remote.network.ApiService
import com.prapps.core.core.data.source.remote.response.MovieDetailResponse
import com.prapps.core.core.data.source.remote.response.ObjekMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getMovie(key: String): Flow<ApiResponse<List<ObjekMovieResponse>>> {
        return flow {
            try {
                val response = apiService.getList(key)
                val dataArray = response.places
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.places))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))

            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(
        movieId: Int,
        language: String,
        key: String
    ): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(movieId, language, key)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


}
