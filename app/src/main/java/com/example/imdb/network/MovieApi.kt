package com.example.imdb.network

import com.example.imdb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    fun getDataFromApi(
        @Query("apikey") apikey: String, @Query("s") query: String
    ): Call<List<Movie>>

}