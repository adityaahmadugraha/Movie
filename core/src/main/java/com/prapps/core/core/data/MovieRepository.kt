package com.prapps.core.core.data


import com.prapps.core.core.data.source.local.LocalDataSource
import com.prapps.core.core.data.source.remote.RemoteDataSource
import com.prapps.core.core.data.source.remote.network.ApiResponse
import com.prapps.core.core.data.source.remote.response.MovieDetailResponse
import com.prapps.core.core.data.source.remote.response.ObjekMovieResponse
import com.prapps.core.core.domain.model.MovieDetail
import com.prapps.core.core.domain.model.MovieFavorite
import com.prapps.core.core.domain.model.MovieList
import com.prapps.core.core.domain.repository.IMovieRepository
import com.prapps.core.core.utils.AppExecutors
import com.prapps.core.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override suspend fun getAllMovie(apiKey: String): Flow<Resource<List<MovieList>>> =
        object : NetworkBoundResourceMovie<List<MovieList>, List<ObjekMovieResponse>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<ObjekMovieResponse>>> =
                remoteDataSource.getMovie(apiKey)

            override fun mapResponseToResult(response: List<ObjekMovieResponse>): List<MovieList> {
                return DataMapper.mapResponseToResult(response)
            }

        }.asFlow()

    override fun getDetailMovie(
        movieId: Int,
        language: String,
        key: String
    ): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {

            override fun shouldFetch(data: MovieDetail?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getMovieDetail(movieId, language, key)
            }

            override fun mapResponseToResult(response: MovieDetailResponse): MovieDetail {
                return DataMapper.mapResponseToResultDetail(response)
            }
        }.asFlow()



    override suspend fun addMovieToFavorites(movie: MovieFavorite) {
        val movieEntity = DataMapper.mapToMovieEntity(movie)
        localDataSource.insertData(movieEntity)
    }

    override suspend fun removeMovieFromFavorites(movie: MovieFavorite) {
        val movieEntity = DataMapper.mapToMovieEntity(movie)
        localDataSource.deleteData(movieEntity)
    }

    override fun getFavoriteMovies(): Flow<List<MovieFavorite>> {
        return localDataSource.getAllData().map { movieEntities ->
            movieEntities.map { movieEntity ->
                DataMapper.mapToMovieFavorite(movieEntity)
            }
        }
    }

}



