package com.example.imdb

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.databinding.FragmentMovieDetailBinding
import com.example.imdb.model.Movie
import com.example.imdb.search.DetailViewModel
import com.squareup.picasso.Picasso


class MovieDetail : Fragment() {
    private lateinit var movieViewModel: DetailViewModel

    private val binding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        movieViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        movieViewModel.search("")
        val movie = arguments?.getSerializable("movie") as Movie
        movieViewModel.getByImdbId(movie.imdbID)
        movieViewModel.search(movie.Title)

        binding.apply {
            suggestions.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            Picasso.with(root.context).load(movie.Poster).into(movieImage)
            Picasso.with(root.context).load(movie.Poster).into(lilPoster)
            year.text = movie.Year
            title.text = movie.Title

            movieViewModel.movieDetail.observe(viewLifecycleOwner) { movie ->
                runtime.text = movie.Runtime
                plot.text = movie.Plot
                plot2.text = movie.Plot
                rate.text = "IMDb: " + movie.imdbRating

                if (movie.Genre.isNullOrBlank().not()) {
                    for (item in movie.Genre!!.split(",")) {
                        val texView = TextView(context)
                        texView.text = item.trim()
                        texView.background = GradientDrawable().apply {
                            cornerRadius = 100000f
                            color = ColorStateList.valueOf(Color.rgb(175, 110, 210))
                        }
                        texView.setPadding(20, 10, 20, 10)
                        texView.setTextColor(Color.WHITE)
                        binding.genre.addView(texView, LinearLayout.LayoutParams(-2, -2).apply {
                            setMargins(0, 0, 20, 0)
                        })
                    }
                }
            }

            movieViewModel.searchData.observe(viewLifecycleOwner) { res ->
                if (res != null) {
                    suggestions.adapter = SuggestionAdapter(res.Search.filter {
                        it.imdbID != movie.imdbID
                    })
                }
            }
        }

        return binding.root
    }

}