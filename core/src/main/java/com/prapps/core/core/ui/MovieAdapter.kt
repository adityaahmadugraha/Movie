package com.prapps.core.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prapps.core.BuildConfig.IMAGE_URL
import com.prapps.core.R
import com.prapps.core.core.domain.model.MovieList
import com.prapps.core.core.utils.DataMapper.createImageProgress
import com.prapps.core.databinding.ListItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieList>()
    var onItemClick: ((MovieList) -> Unit)? = null

    fun setData(newListData: List<MovieList>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(data: MovieList) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(IMAGE_URL + (data.poster_path ?: ""))
                    .placeholder(itemView.context.createImageProgress())
                    .into(poster)
                title.text = data.title ?: "-"
                releaseDate.text = data.release_date ?: "-"
                genre.text = data.popularity

                val voteAverageValue = (data.vote_average as? Number)?.toDouble() ?: 0.0
                val voteAveragePercentage = (voteAverageValue * 10).toFloat()
                ratingProgressBar.setProgressWithAnimation(voteAveragePercentage)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}