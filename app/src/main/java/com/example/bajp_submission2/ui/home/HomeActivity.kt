package com.example.bajp_submission2.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bajp_submission2.databinding.ActivityHomeBinding
import com.example.bajp_submission2.ui.movie.MovieFragment
import com.example.bajp_submission2.ui.tvshow.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViewPager()
    }

    private fun setViewPager() {
        val listFragment = listOf(MovieFragment(), TvShowFragment())
        val titleTab = listOf("Movie", "Tv Show")

        binding.viewPager.adapter =
            ViewPagerAdapter(listFragment, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titleTab[position]
        }.attach()
    }
}