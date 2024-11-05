package com.prapps.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.prapps.core.core.ui.FavoriteAdapter
import com.prapps.movieapps.databinding.FragmentFavoriteBinding
import com.prapps.movieapps.detail.DetailActivity
import com.prapps.movieapps.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val favoriteAdapter = FavoriteAdapter()

            favoriteAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.id)
                startActivity(intent)
            }

            with(binding.rvRoom) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner) { movieList ->
                if (movieList != null && movieList.isNotEmpty()) {
                    favoriteAdapter.setData(movieList)
                    binding.rvRoom.visibility = View.VISIBLE


                } else {
                    binding.rvRoom.visibility = View.GONE

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
