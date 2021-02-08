package com.example.pprecyclerviewmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.precyclerviewmvvm.models.Movie

@Dao
interface MoviesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getMovies(): List<Movie>

}