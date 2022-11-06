package com.education.movie.presentation.fragments.mainscreen

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.education.movie.R
import com.education.movie.data.models.mainscreen.MovieResponse
import kotlinx.android.synthetic.main.main_screen_recyclerview_item.view.main_screen_recyclerview_item
import kotlinx.android.synthetic.main.main_screen_recyclerview_item.view.overview
import kotlinx.android.synthetic.main.main_screen_recyclerview_item.view.poster_imageview
import kotlinx.android.synthetic.main.main_screen_recyclerview_item.view.release_date
import kotlinx.android.synthetic.main.main_screen_recyclerview_item.view.title

typealias OnItemClickListener = (position: Int) -> Unit

class MainScreenAdapter (private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {
    private var moviesList = MovieResponse(0, emptyList(), 0, 0)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_screen_recyclerview_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = moviesList.results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Uri.parse("$BASE_URI_FOR_IMAGES${moviesList.results[position].posterPath}"))
            .centerCrop()
            .into(holder.itemView.poster_imageview)
        holder.itemView.title.text = moviesList.results[position].title
        holder.itemView.overview.text = moviesList.results[position].overview
        holder.itemView.release_date.text = moviesList.results[position].releaseDate
        holder.itemView.main_screen_recyclerview_item.setOnClickListener{
            onItemClickListener(position)
        }
    }

    fun setData(movies: MovieResponse) {
        moviesList = movies
        notifyDataSetChanged()
    }

    companion object{
        const val BASE_URI_FOR_IMAGES = "https://www.themoviedb.org/t/p/w220_and_h330_face/"
    }
}