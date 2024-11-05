package com.prapps.movieapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.prapps.movieapps.databinding.ActivityMainBinding
import com.prapps.movieapps.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.nav_home -> {
                    openFragment(HomeFragment())
                    true
                }

                R.id.nav_favorite -> {
                    try {
                        val favoriteFragmentClass = Class.forName("com.prapps.favorite.FavoriteFragment")
                        val favoriteFragment = favoriteFragmentClass.newInstance() as Fragment
                        openFragment(favoriteFragment)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    true
                }

                else -> false
            }

        }
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}