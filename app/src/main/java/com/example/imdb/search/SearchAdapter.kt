package com.example.imdb.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.model.Movie
import com.google.android.material.textview.MaterialTextView
import com.example.imdb.R

class SearchAdapter(var mList: List<Movie>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_search, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holder.title.text = itemsViewModel.Title
        //holder.textView.text = itemsViewModel.created_at
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: MaterialTextView = this.itemView.findViewById(R.id.movie_title)
        //val textView: MaterialTextView = this.itemView.findViewById(R.id.poster)
    }
}