package com.example.precyclerviewmvvm.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.precyclerviewmvvm.R
import com.example.precyclerviewmvvm.databinding.RecyclerviewMovieBinding
import com.example.precyclerviewmvvm.models.Movie

class MoviesAdapter(
    private val movies: List<Movie>,
    private val listener: RvClick
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = (
            MoviesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recyclerview_movie,
                    parent,
                    false
                )
            )
            )
    /* view binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
            val binding = RecyclerviewMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false)
        return MoviesViewHolder(binding)
    }*/

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    holder.recyclerviewMovieBinding.movie = movies[position]
        holder.itemView.setOnClickListener {
            listener.clicked(movies[position], position)
        }
    }


    inner class MoviesViewHolder(
         val recyclerviewMovieBinding: RecyclerviewMovieBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)
}

