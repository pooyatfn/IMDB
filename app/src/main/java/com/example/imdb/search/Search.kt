package com.example.imdb.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.FragmentSearchBinding
import com.example.imdb.model.Movie

class Search : Fragment() {
    private lateinit var movieViewModel: SearchViewModel
    private lateinit var movieAdapter: SearchAdapter

    private val binding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val recyclerView: RecyclerView = binding.rvSearchItems
        recyclerView.layoutManager = LinearLayoutManager(context)
        movieAdapter = SearchAdapter(emptyList())
        recyclerView.adapter = movieAdapter

        movieViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        movieViewModel.movieDataList.observe(viewLifecycleOwner) { movies ->
            movieAdapter.mList = movies
            movieAdapter.notifyDataSetChanged()
        }

        movieViewModel.getApiData("6f818fe4", "Batman")
        return binding.root
    }

    fun loadData(){
        movieViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        movieViewModel.getApiData("6f818fe4", "Batman")
        movieViewModel.movieDataList.observe(this) {
            initAdapter(it)
        }
    }

    private fun initAdapter(data:List<Movie>){
        binding.rvSearchItems.layoutManager = LinearLayoutManager(context)
        // This will pass the ArrayList to our Adapter
        val adapter = SearchAdapter(data)
        // Setting the Adapter with the recyclerview
        binding.rvSearchItems.adapter = adapter
    }
}
