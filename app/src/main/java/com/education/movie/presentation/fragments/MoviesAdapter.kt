package com.education.movie.presentation.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.education.movie.R
import com.education.movie.data.entity.PageOfMoviesEntity
import com.education.movie.data.utils.Utils.Companion.BASE_URI_FOR_IMAGES
import kotlinx.android.synthetic.main.movies_recyclerview_item.view.poster_imageview
import kotlinx.android.synthetic.main.movies_recyclerview_item.view.title_and_vote


class MoviesAdapter :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private var moviesList = PageOfMoviesEntity(0, emptyList(), 0, 0)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movies_recyclerview_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = moviesList.results.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Uri.parse("$BASE_URI_FOR_IMAGES${moviesList.results[position].posterPath}"))
            .centerCrop()
            .into(holder.itemView.poster_imageview)
        holder.itemView.title_and_vote.text =
            "${moviesList.results[position].title} (${moviesList.results[position].voteAverage})"
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(movies: PageOfMoviesEntity) {
        moviesList = movies
        notifyDataSetChanged()
    }
}