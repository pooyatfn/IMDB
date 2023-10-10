package com.example.imdb.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.MovieDetailDirections
import com.example.imdb.databinding.ItemMovieSearchBinding
import com.example.imdb.model.Movie
import com.squareup.picasso.Picasso

class SearchAdapter(var mList: List<Movie>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val made = mList[position]
        holder.bind(made)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(private val binding: ItemMovieSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.Title
            Picasso.with(binding.root.context).load(movie.Poster).into(binding.poster)
            binding.root.setOnClickListener { view ->
                val action = MovieDetailDirections.actionGlobalMovieDetail(movie)

                view.findNavController().navigate(action)
            }
        }
    }
}