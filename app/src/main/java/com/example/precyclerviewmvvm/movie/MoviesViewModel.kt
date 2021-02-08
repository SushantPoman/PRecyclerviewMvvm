package com.example.precyclerviewmvvm.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.precyclerviewmvvm.models.Movie
import com.example.precyclerviewmvvm.utils.Resource
import kotlinx.coroutines.launch

class MoviesViewModel @ViewModelInject constructor(
    private val repository: MoviesRepository
): ViewModel() {


    private val _movies = MutableLiveData<Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>>
        get() = _movies


    fun getMovies() =
        viewModelScope.launch {
            _movies.value = Resource.Loading
            _movies.value = repository.getMovies()
        }



}