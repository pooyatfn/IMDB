package com.example.imdb.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.model.Movie
import com.example.imdb.network.MovieApi
import com.example.imdb.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    var movieDataList = MutableLiveData<List<Movie>>()

    fun getApiData(apikey: String, query: String){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(MovieApi::class.java)
        retrofitInstance.getDataFromApi(apikey, query).enqueue(object : Callback<List<Movie>>{
            override fun onResponse(
                call: Call<List<Movie>>,
                response: Response<List<Movie>>
            ) {
                movieDataList.value = response.body()
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {

            }

        })

    }

}