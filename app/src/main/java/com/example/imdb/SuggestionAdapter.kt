package com.example.imdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ItemMovieSearchBinding
import com.example.imdb.model.Movie
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class SuggestionAdapter(private val dataSet: Array<Movie>) :
    RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(private val binding: ItemMovieSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.Title
            Picasso.with(binding.root.context).load(movie.Poster).into(binding.poster);
            binding.root.setOnClickListener { view ->
                val title = movie.Title
                val year = movie.Year
                val imdbID = movie.imdbID
                val type = movie.Type
                val poster = movie.Poster

                val json = "{\n" +
                        "    \"Title\": \"Batman Begins\",\n" +
                        "    \"Year\": \"2005\",\n" +
                        "    \"Rated\": \"PG-13\",\n" +
                        "    \"Released\": \"15 Jun 2005\",\n" +
                        "    \"Runtime\": \"140 min\",\n" +
                        "    \"Genre\": \"Action, Crime, Drama\",\n" +
                        "    \"Director\": \"Christopher Nolan\",\n" +
                        "    \"Writer\": \"Bob Kane, David S. Goyer, Christopher Nolan\",\n" +
                        "    \"Actors\": \"Christian Bale, Michael Caine, Ken Watanabe\",\n" +
                        "    \"Plot\": \"After witnessing his parents' death, Bruce learns the art of fighting to confront injustice. When he returns to Gotham as Batman, he must stop a secret society that intends to destroy the city.\",\n" +
                        "    \"Language\": \"English, Mandarin\",\n" +
                        "    \"Country\": \"United States, United Kingdom\",\n" +
                        "    \"Awards\": \"Nominated for 1 Oscar. 14 wins & 79 nominations total\",\n" +
                        "    \"Poster\": \"https://m.media-amazon.com/images/M/MV5BOTY4YjI2N2MtYmFlMC00ZjcyLTg3YjEtMDQyM2ZjYzQ5YWFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\",\n" +
                        "    \"Ratings\": [\n" +
                        "        {\n" +
                        "            \"Source\": \"Internet Movie Database\",\n" +
                        "            \"Value\": \"8.2/10\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"Source\": \"Rotten Tomatoes\",\n" +
                        "            \"Value\": \"85%\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"Source\": \"Metacritic\",\n" +
                        "            \"Value\": \"70/100\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"Metascore\": \"70\",\n" +
                        "    \"imdbRating\": \"8.2\",\n" +
                        "    \"imdbVotes\": \"1,535,514\",\n" +
                        "    \"imdbID\": \"tt0372784\",\n" +
                        "    \"Type\": \"movie\",\n" +
                        "    \"DVD\": \"09 Sep 2009\",\n" +
                        "    \"BoxOffice\": \"\$206,863,479\",\n" +
                        "    \"Production\": \"N/A\",\n" +
                        "    \"Website\": \"N/A\",\n" +
                        "    \"Response\": \"True\"\n" +
                        "}"

                val gson = Gson()
                val movieToSend = gson.fromJson(json, Movie::class.java)

                val action = MovieDetailDirections.actionGlobalMovieDetail(
                    movieToSend.Title, movieToSend.Year, movieToSend.imdbID,
                    movieToSend.Type, movieToSend.Poster, movieToSend.Runtime,
                    movieToSend.Plot, movieToSend.Genre
                )

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
