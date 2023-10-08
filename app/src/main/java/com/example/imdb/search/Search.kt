package com.example.imdb.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val recyclerView: RecyclerView = binding.rvSearchItems

        recyclerView.layoutManager = GridLayoutManager(context, 3)
        movieAdapter = SearchAdapter(emptyList())
        recyclerView.adapter = movieAdapter

        movieViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        movieViewModel.movieDataList.observe(viewLifecycleOwner) { movies ->
            movieAdapter.mList = movies.Search
            movieAdapter.notifyDataSetChanged()
        }



        val searchView = binding.searchBar
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Not used in this example
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieAdapter.filter.filter(newText)
                return true
            }
        })

        movieViewModel.getApiData("6f818fe4", "Batman")
        return binding.root
    }

    private fun loadData() {
        binding.rvSearchItems.layoutManager = GridLayoutManager(context, 3)
        // This will pass the ArrayList to our Adapter
        val adapter = SearchAdapter(emptyList())
        binding.rvSearchItems.adapter = adapter
        movieViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        movieViewModel.getApiData("6f818fe4", "Batman")
        movieViewModel.movieDataList.observe(viewLifecycleOwner) { movies ->
            movieAdapter.mList = movies.Search
            movieAdapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter(data: List<Movie>) {
        binding.rvSearchItems.layoutManager = GridLayoutManager(context, 3)
        // This will pass the ArrayList to our Adapter
        val adapter = SearchAdapter(data)
        // Setting the Adapter with the recyclerview
        binding.rvSearchItems.adapter = adapter
    }
}
