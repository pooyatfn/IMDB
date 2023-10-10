package com.example.imdb.network

import com.example.imdb.model.Movie
import com.example.imdb.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    fun search(
        @Query("apikey") apikey: String, @Query("s") query: String
    ): Call<MovieResponse>

    @GET("/")
    fun getByImdbId(
        @Query("apikey") apikey: String, @Query("i") imdbId: String
    ): Call<Movie>

}