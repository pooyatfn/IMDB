package com.example.imdb.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.MovieDetail
import com.example.imdb.MovieDetailDirections
import com.example.imdb.databinding.ItemMovieSearchBinding
import com.example.imdb.model.Movie
import com.squareup.picasso.Picasso
import java.util.Locale

class SearchAdapter(var mList: List<Movie>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(), Filterable{

    var filteredList = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val made = filteredList[position]
        holder.bind(made)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    // Holds the views for adding it to image and text
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

                val action = MovieDetailDirections.actionGlobalMovieDetail(
                    title, type, poster, year, imdbID
                )

                view.findNavController().navigate(action)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase(Locale.getDefault())
                val filteredData: List<Movie> = if (query.isEmpty()) {
                    emptyList()
                } else {
                    mList.filter {
                        it.Title.lowercase(Locale.getDefault()).contains(query)
                    }
                }
                val results = FilterResults()
                results.values = filteredData
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Movie>
                notifyDataSetChanged()

                //listener.onFilteredList(filteredList)
            }
        }
    }

    interface OnFilteredListListener {
        fun onFilteredList(filteredData: List<Movie>)
    }
}