package com.example.imdb.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.model.MovieResponse
import com.example.imdb.network.MovieApi
import com.example.imdb.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ApiKey = "6f818fe4"

open class SearchViewModel : ViewModel() {
    private var call: Call<MovieResponse>? = null
    var searchData = MutableLiveData<MovieResponse?>()

    fun search(query: String) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(MovieApi::class.java)

        call?.cancel()
        call = retrofitInstance.search(ApiKey, query)
        call!!.enqueue(object : Callback<MovieResponse?> {
            override fun onResponse(call: Call<MovieResponse?>, response: Response<MovieResponse?>) {

                searchData.value = response.body()

                //Log.i(LOG_TAG, response.body()!!)
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                //Log.e(LOG_TAG, t.message!!)
            }
        })
    }
}