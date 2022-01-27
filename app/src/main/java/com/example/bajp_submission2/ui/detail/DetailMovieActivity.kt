package com.example.bajp_submission2.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bajp_submission2.BuildConfig
import com.example.bajp_submission2.data.source.local.DetailEntity
import com.example.bajp_submission2.databinding.ActivityDetailMovieBinding
import com.example.bajp_submission2.ui.detail.DetailMovieViewModel.Companion.MOVIE
import com.example.bajp_submission2.viewmodel.ViewModelFactory
import com.google.android.material.appbar.AppBarLayout


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private var isShow = true
    private var scrollRange = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        supportActionBar?.hide()
        activityDetailMovieBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        showProgressBar(true)

        val extras = intent.extras
        if (extras != null) {
            val contentId = extras.getString(EXTRA_MOVIE)
            val contentCategory = extras.getString(EXTRA_CATEGORY)

            if (contentId != null && contentCategory != null) {
                viewModel.setContent(contentId, contentCategory)
                viewModel.getContentDetail().observe(this) { content ->
                    showProgressBar(false)
                    populateContentDetail(content)
                }
            }

            if (extras.getString(EXTRA_CATEGORY) == MOVIE) {
                showTitleCollapse("Detail Movie")
            } else {
                showTitleCollapse("Detail Tv Show")
            }
        }

    }

    private fun showProgressBar(state: Boolean) {
        activityDetailMovieBinding.progressBar.isVisible = state
        activityDetailMovieBinding.appbar.isGone = state
        activityDetailMovieBinding.nestedScrollView.isGone = state
    }

    private fun populateContentDetail(content: DetailEntity) {
        activityDetailMovieBinding.detailTitle.text = content.title
        activityDetailMovieBinding.detailGenre.text =
            content.genre.toString().replace("[", "").replace("]", "")
        activityDetailMovieBinding.detailsReleaseDate.text = content.date
        activityDetailMovieBinding.detailDescription.text = content.description
        activityDetailMovieBinding.detailScore.text = content.score.toString()

        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + content.imagePath)
            .into(activityDetailMovieBinding.ivDetailToolbar)


        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + content.imagePath)
            .into(activityDetailMovieBinding.detailPoster)
    }


    fun showTitleCollapse(statusBar: String) {
        activityDetailMovieBinding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                activityDetailMovieBinding.collapsingToolbar.title = statusBar
                isShow = true
            } else if (isShow) {
                activityDetailMovieBinding.collapsingToolbar.title = " "
                isShow = false
            }
        })
    }

}