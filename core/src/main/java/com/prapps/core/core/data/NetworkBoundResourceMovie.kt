package com.prapps.core.core.data

import com.prapps.core.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResourceMovie<ResultType, RequestType> {

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
}
