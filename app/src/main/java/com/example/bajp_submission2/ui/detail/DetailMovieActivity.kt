package com.example.bajp_submission2.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bajp_submission2.data.source.local.ContentEntity
import com.example.bajp_submission2.databinding.ActivityDetailMovieBinding
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

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val contentId = extras.getString(EXTRA_MOVIE)
            val contentCategory = extras.getString(EXTRA_CATEGORY)

            if (contentId != null && contentCategory != null) {
                viewModel.setContent(contentId, contentCategory)
                val content = viewModel.getContentDetail()
                populateContentDetail(content)
            }
        }
        showTitleCollapse()
    }

    private fun populateContentDetail(content: ContentEntity) {
        activityDetailMovieBinding.detailTitle.text = content.title
        activityDetailMovieBinding.detailGenre.text = content.genre.toString()
        activityDetailMovieBinding.detailsReleaseDate.text = content.date
        activityDetailMovieBinding.detailDescription.text = content.description
        activityDetailMovieBinding.detailScore.text = content.score.toString()

        Glide.with(this)
            .load(content.imagePath)
            .into(activityDetailMovieBinding.ivDetailToolbar)


        Glide.with(this)
            .load(content.imagePath)
            .into(activityDetailMovieBinding.detailPoster)
    }


    private fun showTitleCollapse() {
        activityDetailMovieBinding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                activityDetailMovieBinding.collapsingToolbar.title = "Detail Movie"
                isShow = true
            } else if (isShow) {
                activityDetailMovieBinding.collapsingToolbar.title = " "
                isShow = false
            }
        })
    }
}