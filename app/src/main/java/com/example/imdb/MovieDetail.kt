package com.example.imdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginRight
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.databinding.FragmentMovieDetailBinding
import com.example.imdb.model.Movie
import com.example.imdb.search.SearchViewModel
import com.squareup.picasso.Picasso


class MovieDetail : Fragment() {
    private lateinit var movieViewModel: SearchViewModel

    private val binding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val Title = arguments?.getString("title")
        val Type = arguments?.getString("type")
        val Poster = arguments?.getString("poster")
        val Year = arguments?.getString("year")
        val imdbID = arguments?.getString("imdbID")
        val Runtime = arguments?.getString("runtime")
        val Plot = arguments?.getString("plot")
        val Genre = arguments?.getString("genre")?.split(", ")

        if (Genre != null) {
            for (item in Genre) {
                val texView = TextView(context)
                texView.text = item
                binding.genre.addView(texView)
            }
        }

        val movie1 = Movie("The Batman", "2005", "tt0372784",
            "", "140 min", "Action, Crime, Drama", "", "",
            "", "After witnessing his parents' death, Bruce learns the art of fighting to confront injustice. When he returns to Gotham as Batman, he must stop a secret society that intends to destroy the city.",
            "", "", "",
            "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_SX300.jpg",
            "", "", "movie")

        val movie2 = Movie("Batman", "2005", "tt0372784",
            "", "140 min", "Action, Crime, Drama", "", "",
            "", "After witnessing his parents' death, Bruce learns the art of fighting to confront injustice. When he returns to Gotham as Batman, he must stop a secret society that intends to destroy the city.",
            "", "", "",
            "https://m.media-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg",
            "", "", "movie")

        val movie3 = Movie("Batman Returns", "2005", "tt0372784",
            "", "140 min", "Action, Crime, Drama", "", "",
            "", "After witnessing his parents' death, Bruce learns the art of fighting to confront injustice. When he returns to Gotham as Batman, he must stop a secret society that intends to destroy the city.",
            "", "", "",
            "https://m.media-amazon.com/images/M/MV5BOGZmYzVkMmItM2NiOS00MDI3LWI4ZWQtMTg0YWZkODRkMmViXkEyXkFqcGdeQXVyODY0NzcxNw@@._V1_SX300.jpg",
            "", "", "movie")

        val movie4 = Movie("The Lego Batman Movie", "2005", "tt0372784",
            "", "140 min", "Action, Crime, Drama", "", "",
            "", "After witnessing his parents' death, Bruce learns the art of fighting to confront injustice. When he returns to Gotham as Batman, he must stop a secret society that intends to destroy the city.",
            "", "", "",
            "https://m.media-amazon.com/images/M/MV5BMTcyNTEyOTY0M15BMl5BanBnXkFtZTgwOTAyNzU3MDI@._V1_SX300.jpg",
            "", "", "movie")
        val dateset = arrayOf(movie1, movie2, movie3, movie4)
        val customAdapter = SuggestionAdapter(dateset)

        binding.apply {
            Picasso.with(root.context).load(Poster).into(movieImage)
            Picasso.with(root.context).load(Poster).into(lilPoster)
            year.text = Year
            runtime.text = Runtime
            plot.text = Plot
            title.text = Title
            plot2.text = Plot
            suggestions.adapter = customAdapter
            suggestions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        return binding.root
    }

}