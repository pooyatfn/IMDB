package com.example.imdb.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.model.MovieResponse
import com.example.imdb.network.MovieApi
import com.example.imdb.network.RetrofitInstance
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchViewModel : ViewModel() {
    var movieDataList = MutableLiveData<MovieResponse>()

    fun getApiData(apikey: String, query: String) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(MovieApi::class.java)

        val json = """{
    "Search": [
        {
            "Title": "Batman Begins",
            "Year": "2005",
            "imdbID": "tt0372784",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BOTY4YjI2N2MtYmFlMC00ZjcyLTg3YjEtMDQyM2ZjYzQ5YWFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
        },
        {
            "Title": "Batman v Superman: Dawn of Justice",
            "Year": "2016",
            "imdbID": "tt2975590",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
        },
        {
            "Title": "The Batman",
            "Year": "2022",
            "imdbID": "tt1877830",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_SX300.jpg"
        },
        {
            "Title": "Batman",
            "Year": "1989",
            "imdbID": "tt0096895",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg"
        },
        {
            "Title": "Batman Returns",
            "Year": "1992",
            "imdbID": "tt0103776",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BOGZmYzVkMmItM2NiOS00MDI3LWI4ZWQtMTg0YWZkODRkMmViXkEyXkFqcGdeQXVyODY0NzcxNw@@._V1_SX300.jpg"
        },
        {
            "Title": "Batman & Robin",
            "Year": "1997",
            "imdbID": "tt0118688",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BMGQ5YTM1NmMtYmIxYy00N2VmLWJhZTYtN2EwYTY3MWFhOTczXkEyXkFqcGdeQXVyNTA2NTI0MTY@._V1_SX300.jpg"
        },
        {
            "Title": "Batman Forever",
            "Year": "1995",
            "imdbID": "tt0112462",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BNDdjYmFiYWEtYzBhZS00YTZkLWFlODgtY2I5MDE0NzZmMDljXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
        },
        {
            "Title": "The Lego Batman Movie",
            "Year": "2017",
            "imdbID": "tt4116284",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BMTcyNTEyOTY0M15BMl5BanBnXkFtZTgwOTAyNzU3MDI@._V1_SX300.jpg"
        },
        {
            "Title": "Batman: The Animated Series",
            "Year": "1992–1995",
            "imdbID": "tt0103359",
            "Type": "series",
            "Poster": "https://m.media-amazon.com/images/M/MV5BOTM3MTRkZjQtYjBkMy00YWE1LTkxOTQtNDQyNGY0YjYzNzAzXkEyXkFqcGdeQXVyOTgwMzk1MTA@._V1_SX300.jpg"
        },
        {
            "Title": "Batman v Superman: Dawn of Justice (Ultimate Edition)",
            "Year": "2016",
            "imdbID": "tt18689424",
            "Type": "movie",
            "Poster": "https://m.media-amazon.com/images/M/MV5BOTRlNWQwM2ItNjkyZC00MGI3LThkYjktZmE5N2FlMzcyNTIyXkEyXkFqcGdeQXVyMTEyNzgwMDUw._V1_SX300.jpg"
        }
    ],
    "totalResults": "545",
    "Response": "True"
}"""
        val gson = Gson()
        movieDataList.value = gson.fromJson(json, MovieResponse::class.java)

//        val stringCall: Call<String> = retrofitInstance.getDataFromApi(apikey, query)
//        stringCall.enqueue(object : Callback<String?> {
//            override fun onResponse(call: Call<String?>, response: Response<String?>) {
//                val gson = Gson()
//
//
//                movieDataList.value = gson.fromJson(json, MovieResponse::class.java)
//
//                //Log.i(LOG_TAG, response.body()!!)
//            }
//
//            override fun onFailure(call: Call<String?>, t: Throwable) {
//                //Log.e(LOG_TAG, t.message!!)
//            }
//        })

//        runBlocking {
//            launch(Dispatchers.Default) {
//                val json = retrofitInstance.getDataFromApi(apikey, query).toString()
//                val gson = Gson()
//                movieDataList.value = gson.fromJson(json, MovieResponse::class.java)
//                println(movieDataList)
//            }
//        }
//        retrofitInstance.getDataFromApi(apikey, query).enqueue(object : Callback<String> {
//            override fun onResponse(
//                call: Call<String>,
//                response: Response<String>
//            ) {
//                println("ye ")
//                val gson = Gson()
//                val json = response.body()
//                movieDataList.value = gson.fromJson(json, MovieResponse::class.java)
//                println(json)
//                println("sdfsda")
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                println()
//            }
//
//        })

    }

}