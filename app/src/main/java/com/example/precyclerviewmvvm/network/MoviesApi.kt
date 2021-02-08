package com.example.precyclerviewmvvm.network

import com.example.precyclerviewmvvm.models.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("movies")
    suspend fun getMovies() : List<Movie>

}