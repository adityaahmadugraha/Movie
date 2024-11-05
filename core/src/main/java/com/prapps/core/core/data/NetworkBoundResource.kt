package com.prapps.core.core.data

import com.prapps.core.core.data.source.remote.network.ApiResponse
import com.prapps.core.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(mapResponseToResult(apiResponse.data)))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Error("No data available"))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    protected open fun mapResponseToResult(response: RequestType): ResultType {
        return response as ResultType
    }

    fun asFlow(): Flow<Resource<ResultType>> = result

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    //    protected abstract suspend fun saveCallResult(data: RequestType)
//    abstract fun loadFromDB(): Flow<MovieDetail?>
    abstract fun shouldFetch(data: MovieDetail?): Boolean
}


