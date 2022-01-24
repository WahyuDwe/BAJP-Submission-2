package com.example.bajp_submission2.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bajp_submission2.data.ContentEntity
import com.example.bajp_submission2.databinding.ItemContentBinding


class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShow = ArrayList<ContentEntity>()

    fun setTvShow(tvShow: ArrayList<ContentEntity>?) {
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
        fun bind(tvShow: ContentEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemGenre.text = tvShow.genre
                tvItemScore.text = tvShow.score
                tvItemDate.text = tvShow.date

                Glide.with(itemView.context)
                    .load(tvShow.imagePath)
                    .into(ivItemPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, tvShow.id)
                    intent.putExtra(DetailMovieActivity.EXTRA_CATEGORY, TV_SHOW)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}