package com.example.precyclerviewmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.pprecyclerviewmvvm.db.UserPreferences
import com.example.precyclerviewmvvm.databinding.ActivityMainBinding
import com.example.precyclerviewmvvm.movie.MoviesViewModel
import com.example.precyclerviewmvvm.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MoviesViewModel by viewModels()

    /*Dagger does not support injection into private fields
    * so that following variable we can not declare as private*/
    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            Log.e("#Check", /*userPreferences.lastUpdatedAt.first()*/  " sushat create ")
//            userPreferences.clear()
        }

        /*viewModel.getMovies()

        viewModel.movies.observe(this, Observer {

            when(it){
                is Resource.Success ->
                    Toast.makeText(this, "${it.data?.size}", Toast.LENGTH_SHORT).show()
                is Resource.Error ->
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()

            }
        })*/

    }

    override fun onStop() {
        super.onStop()

        /*When application kills clear data store.
        * Uncomment this only when you want to check data store clear function
        * */

        /*lifecycleScope.launch() {
            Log.e("#Check", *//*userPreferences.lastUpdatedAt.first()*//*  " sushat destroy ")
            userPreferences.clear()
        }*/

    }
}