package com.education.movie.presentation.fragments.popularmovies

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.education.movie.R
import com.education.movie.data.models.popularmovies.ListOfMoviesResponse
import com.education.movie.data.models.popularmovies.PageOfMoviesResponse
import java.util.ArrayList
import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.main_screen_recyclerview_item
import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.overview
import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.poster_imageview
import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.release_date
import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.title

typealias OnItemClickListener = (position: Int) -> Unit

class PopularMoviesAdapter (private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {
    private var listOfMovies : ArrayList<ListOfMoviesResponse> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.popular_movies_recyclerview_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = listOfMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Uri.parse("$BASE_URI_FOR_IMAGES${listOfMovies[position].posterPath}"))
            .centerCrop()
            .into(holder.itemView.poster_imageview)
        holder.itemView.title.text = listOfMovies[position].title
        holder.itemView.overview.text = listOfMovies[position].overview
        holder.itemView.release_date.text = listOfMovies[position].releaseDate
        holder.itemView.main_screen_recyclerview_item.setOnClickListener{
            onItemClickListener(position)
        }
    }

    fun setData(movies: ArrayList<ListOfMoviesResponse>) {
        listOfMovies = movies
        notifyDataSetChanged()
    }

    companion object{
        const val BASE_URI_FOR_IMAGES = "https://www.themoviedb.org/t/p/w220_and_h330_face/"
    }
}