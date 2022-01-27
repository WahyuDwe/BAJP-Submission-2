package com.example.bajp_submission2.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bajp_submission2.BuildConfig
import com.example.bajp_submission2.data.source.local.TvShowEntity
import com.example.bajp_submission2.databinding.ItemContentBinding
import com.example.bajp_submission2.ui.detail.DetailMovieActivity
import com.example.bajp_submission2.ui.detail.DetailMovieViewModel.Companion.TV_SHOW


class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(tvShow: List<TvShowEntity>?) {
        if (tvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemContentBinding =
            ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemContentBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemScore.text = tvShow.score.toString()
                tvItemDate.text = tvShow.date
                tvItemDesc.text = tvShow.overview

                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + tvShow.imagePath)
                    .into(ivItemPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, tvShow.id.toString())
                    intent.putExtra(DetailMovieActivity.EXTRA_CATEGORY, TV_SHOW)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}