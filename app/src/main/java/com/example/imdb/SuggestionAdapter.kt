package com.example.imdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ItemMovieSearchBinding
import com.example.imdb.model.Movie
import com.squareup.picasso.Picasso

class SuggestionAdapter(private val dataSet: List<Movie>) :
    RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(private val binding: ItemMovieSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.Title
            Picasso.with(binding.root.context).load(movie.Poster)
                .resize(120, 200)
                .centerCrop().into(binding.poster)
            binding.root.setOnClickListener { view ->
                val action = MovieDetailDirections.actionGlobalMovieDetail(movie)

                view.findNavController().navigate(action)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val made = dataSet[position]
        viewHolder.bind(made)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
