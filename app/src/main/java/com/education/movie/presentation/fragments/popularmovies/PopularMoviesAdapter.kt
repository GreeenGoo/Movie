package com.education.movie.presentation.fragments.popularmovies

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.education.movie.R
import com.education.movie.data.models.popularmovies.ListOfMoviesResponse
import com.education.movie.data.models.popularmovies.PageOfMoviesResponse
import com.education.movie.databinding.PopularMoviesRecyclerviewItemBinding
import java.util.ArrayList
//import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.main_screen_recyclerview_item
//import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.overview
//import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.poster_imageview
//import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.release_date
//import kotlinx.android.synthetic.main.popular_movies_recyclerview_item.view.title

typealias OnItemClickListener = (position: Int) -> Unit

class PopularMoviesAdapter (private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {
    private var listOfMovies : ArrayList<ListOfMoviesResponse> = ArrayList()

    inner class ViewHolder(val binding: PopularMoviesRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)
    private var binding : PopularMoviesRecyclerviewItemBinding ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            binding = PopularMoviesRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding!!)

    }

    override fun getItemCount(): Int = listOfMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder){
            with(listOfMovies[position]){
                Glide.with(holder.itemView.context)
            .load(Uri.parse("$BASE_URI_FOR_IMAGES${listOfMovies[position].posterPath}"))
            .centerCrop()
            .into(binding.posterImageview)
        binding.title.text = listOfMovies[position].title
//        binding.overview.text = listOfMovies[position].overview
//        binding.releaseDate.text = listOfMovies[position].releaseDate
        binding.mainScreenRecyclerviewItem.setOnClickListener{
            onItemClickListener(position)
        }
            }
        }
//        Glide.with(holder.itemView.context)
//            .load(Uri.parse("$BASE_URI_FOR_IMAGES${listOfMovies[position].posterPath}"))
//            .centerCrop()
//            .into(holder.itemView.poster_imageview)
//        holder.itemView..text = listOfMovies[position].title
//        holder.itemView.overview.text = listOfMovies[position].overview
//        holder.itemView.release_date.text = listOfMovies[position].releaseDate
//        holder.itemView.main_screen_recyclerview_item.setOnClickListener{
//            onItemClickListener(position)
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(movies: ArrayList<ListOfMoviesResponse>) {
        listOfMovies = movies
        notifyDataSetChanged()
    }

    companion object{
        const val BASE_URI_FOR_IMAGES = "https://www.themoviedb.org/t/p/w220_and_h330_face/"
    }
}