package com.example.imdb.search

import androidx.lifecycle.MutableLiveData
import com.example.imdb.model.Movie
import com.example.imdb.network.MovieApi
import com.example.imdb.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : SearchViewModel() {
    var movieDetail = MutableLiveData<Movie>()

    fun getByImdbId(id: String) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(MovieApi::class.java)

        val stringCall: Call<Movie> = retrofitInstance.getByImdbId(ApiKey, id)
        stringCall.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {

                movieDetail.value = response.body()

                //Log.i(LOG_TAG, response.body()!!)
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                //Log.e(LOG_TAG, t.message!!)
            }
        })
    }
}