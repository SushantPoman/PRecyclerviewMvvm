package com.example.precyclerviewmvvm.movie

import android.util.Log
import com.example.pprecyclerviewmvvm.db.AppDatabase
import com.example.pprecyclerviewmvvm.db.UserPreferences
import com.example.precyclerviewmvvm.models.Movie
import com.example.precyclerviewmvvm.network.MoviesApi
import com.example.precyclerviewmvvm.network.SafeApiCall
import com.example.precyclerviewmvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val api: MoviesApi,
    private val db: AppDatabase,
    private val userPreferences: UserPreferences
):SafeApiCall {

    private val MINIMUM_INTERVAL = 6


    //    private suspend fun fetchMovieApi() = safeApiCall { api.getMovies() }
    private suspend fun fetchMovieApi() {
        var list: Resource<List<Movie>> = safeApiCall { api.getMovies() }
        withContext(Dispatchers.IO) {
            when (list) {
                is Resource.Success -> {
                    userPreferences.saveLastUpdated(LocalDateTime.now().toString())
                    db.getMoviesDao().saveAllMovies(list.value)
                }
                else -> Log.e("#Error", "Inserting into db")
            }
        }
    }

    suspend fun getMovies(): Resource.Success<List<Movie>> {

        val lastSavedAt = userPreferences.lastUpdatedAt.first()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            fetchMovieApi()
        }

        return withContext(Dispatchers.IO) {
            Resource.Success(db.getMoviesDao().getMovies())
        }

    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }
/*
    suspend fun getMovies() : Resource<List<Movie>> {
        return try {

//            val response = api.getMovies()
//            val result = response.body()
//            if(response.isSuccessful && result != null) {
//            Resource.Success(response)
//            }

            val response = api.getMovies()
            Resource.Success(response)
        }catch (throwable: Throwable){
            when (throwable) {
                is HttpException -> {
                    Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                }
                else -> {
                    Resource.Failure(true, null, null)
                }
            }
        }

    }
*/


}