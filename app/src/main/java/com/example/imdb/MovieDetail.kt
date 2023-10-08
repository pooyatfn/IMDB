package com.example.imdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.imdb.databinding.FragmentMovieDetailBinding

class MovieDetail : Fragment() {

    private val binding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val title = arguments?.getString("title")
        val year = arguments?.getString("year")
        val imdbID = arguments?.getString("imdbID")
        val type = arguments?.getString("type")
        val poster = arguments?.getString("poster")



        return binding.root
    }

}