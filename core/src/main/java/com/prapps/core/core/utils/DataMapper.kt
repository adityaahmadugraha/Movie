package com.prapps.core.core.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.prapps.core.core.data.source.local.entity.MovieEntity
import com.prapps.core.core.data.source.remote.response.MovieDetailResponse
import com.prapps.core.core.data.source.remote.response.ObjekMovieResponse
import com.prapps.core.core.domain.model.MovieDetail
import com.prapps.core.core.domain.model.MovieFavorite
import com.prapps.core.core.domain.model.MovieList


object DataMapper {

    fun mapResponseToResult(response: List<ObjekMovieResponse>): List<MovieList> {
        return response.map { objekMovie ->
            MovieList(
                id = objekMovie.id,
                overview = objekMovie.overview,
                title = objekMovie.title,
                poster_path = objekMovie.posterPath,
                release_date = objekMovie.release_date,
                popularity = objekMovie.popularity,
                vote_average = objekMovie.vote_average
            )
        }
    }


    fun mapResponseToResultDetail(response: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            id = response.id,
            overview = response.overview,
            popularity = response.popularity.toString(),
            poster_path = response.poster_path.toString(),
            backdrop_path = response.backdrop_path.toString(),
            tagline = response.tagline.toString(),
            title = response.title,
            release_date = response.release_date,
            vote_average = response.vote_average,
        )
    }

    fun mapToMovieEntity(movieFavorite: MovieFavorite): MovieEntity {
        return MovieEntity(
            id = movieFavorite.id,
            overview = movieFavorite.overview,
            title = movieFavorite.title,
            posterPath = movieFavorite.posterPath,
            release_date = movieFavorite.releaseDate,
            popularity = movieFavorite.popularity,
            vote_average = movieFavorite.voteAverage
        )
    }

    fun mapToMovieFavorite(movieEntity: MovieEntity): MovieFavorite {
        return MovieFavorite(
            id = movieEntity.id,
            overview = movieEntity.overview,
            title = movieEntity.title,
            posterPath = movieEntity.posterPath,
            releaseDate = movieEntity.release_date,
            popularity = movieEntity.popularity,
            voteAverage = movieEntity.vote_average
        )
    }

    val api_key: String = "f189e97d44a042fb35e8e409f0dcfff3"
    val language: String = "en-US"


    fun Context.createImageProgress(): CircularProgressDrawable {
        return CircularProgressDrawable(this).apply {
            strokeWidth = 10f
            centerRadius = 30f
            start()
        }
    }
}



