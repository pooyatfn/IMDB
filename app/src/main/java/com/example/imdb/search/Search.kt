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
        movieViewModel.searchData.observe(viewLifecycleOwner) { movies ->
            movieAdapter.mList = movies?.Search ?: emptyList()
            movieAdapter.notifyDataSetChanged()

            if (movieAdapter.mList.isEmpty()) {
                binding.emptyState.visibility = View.VISIBLE
                binding.rvSearchItems.visibility = View.GONE
            } else {
                binding.emptyState.visibility = View.GONE
                binding.rvSearchItems.visibility = View.VISIBLE
            }
        }

        val searchView = binding.searchBar
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Not used in this example
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieViewModel.search(newText.orEmpty())
                return true
            }
        })

        return binding.root
    }

}
