package com.example.precyclerviewmvvm.movie

import com.example.precyclerviewmvvm.models.Movie

interface RvClick {
    fun clicked(movie: Movie, position: Int)
}