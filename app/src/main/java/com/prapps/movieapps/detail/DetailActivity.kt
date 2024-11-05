package com.prapps.movieapps.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.prapps.core.BuildConfig.IMAGE_URL
import com.prapps.core.core.data.Resource
import com.prapps.core.core.domain.model.MovieDetail
import com.prapps.core.core.domain.model.MovieFavorite
import com.prapps.core.core.utils.DataMapper.api_key
import com.prapps.core.core.utils.DataMapper.createImageProgress
import com.prapps.core.core.utils.DataMapper.language
import com.prapps.movieapps.R
import com.prapps.movieapps.databinding.ActivityDetailBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var movieDetailResponse: MovieDetail? = null
    private var isFavorited: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_DATA, 0)
        if (movieId != 0) {
            fetchMovieDetails(movieId)
        }

        binding.apply {
            likeButton.setOnClickListener {
                movieDetailResponse?.let { movieDetail ->
                    val movieFavorite = MovieFavorite(
                        id = movieDetail.id,
                        overview = movieDetail.overview,
                        title = movieDetail.title,
                        posterPath = movieDetail.poster_path,
                        releaseDate = movieDetail.release_date,
                        popularity = movieDetail.popularity,
                        voteAverage = movieDetail.vote_average
                    )

                    if (isFavorited) {
                        detailViewModel.removeMovieFromFavorites(movieFavorite)
                        isFavorited = false
                        likeButton.setImageResource(R.drawable.ic_favorite_white)
                    } else {
                        detailViewModel.addMovieToFavorites(movieFavorite)
                        isFavorited = true
                        likeButton.setImageResource(R.drawable.ic_favorite_red)
                    }
                }
            }

            icBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        detailViewModel.getDetailMovie(movieId, language, api_key).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    resource.data?.let { movieDetail ->
                        movieDetailResponse = movieDetail
                        showDetailMovie(movieDetail)
                        checkFavoriteStatus(movieDetail.id)
                    }
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun checkFavoriteStatus(movieId: Int) {
        detailViewModel.getFavoriteMovies().onEach { favoriteMovies ->
            isFavorited = favoriteMovies.any { it.id == movieId }
            binding.likeButton.setImageResource(
                if (isFavorited) R.drawable.ic_favorite_red else R.drawable.ic_favorite_white
            )
        }.launchIn(lifecycleScope)
    }

    private fun showDetailMovie(data: MovieDetail) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + (data.backdrop_path ?: ""))
                .placeholder(this@DetailActivity.createImageProgress())
                .into(binding.imgBackground)
            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + (data.poster_path ?: ""))
                .placeholder(this@DetailActivity.createImageProgress())
                .into(binding.imgPoster)
            tvMovieTitle.text = data.title
            tvDate.text = data.release_date
            tvPopularity.text = "Popularity: ${data.popularity}"
            tvMovieOverview.text = data.overview
            tvBy.text = data.tagline
        }
    }
}
