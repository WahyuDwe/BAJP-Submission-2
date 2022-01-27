package com.example.bajp_submission2.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bajp_submission2.BuildConfig
import com.example.bajp_submission2.data.source.local.DetailEntity
import com.example.bajp_submission2.data.source.local.MovieEntity
import com.example.bajp_submission2.databinding.ItemContentBinding
import com.example.bajp_submission2.ui.detail.DetailMovieActivity
import com.example.bajp_submission2.ui.detail.DetailMovieViewModel.Companion.MOVIE

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<MovieEntity>()

    fun setMovies(movie: List<MovieEntity>?) {
        if (movie == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemContentBinding =
            ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemContentBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemScore.text = movie.score.toString()
                tvItemDate.text = movie.date
                tvItemDesc.text = movie.description

                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + movie.imagePath)
                    .into(ivItemPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.id.toString())
                    intent.putExtra(DetailMovieActivity.EXTRA_CATEGORY, MOVIE)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}