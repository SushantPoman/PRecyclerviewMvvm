package com.example.precyclerviewmvvm.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.precyclerviewmvvm.R
import com.example.precyclerviewmvvm.databinding.MovieFragmentBinding
import com.example.precyclerviewmvvm.models.Movie
import com.example.precyclerviewmvvm.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_fragment.*

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.movie_fragment), RvClick {

    private lateinit var binding: MovieFragmentBinding
    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MovieFragmentBinding.bind(view)
        binding.progressbar.visibility = View.GONE

        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Success -> {
                    Toast.makeText(activity, "${it.value.size}", Toast.LENGTH_SHORT).show()
                    if(it != null && it.value.isNotEmpty()) {
                        binding.progressbar.visibility = View.GONE
                        val adapter = MoviesAdapter(it.value, this)
                        rv_movies.adapter = adapter

                    }else
                        Toast.makeText(activity, "Data size is 0", Toast.LENGTH_SHORT).show()

                }

                is Resource.Loading ->
                    binding.progressbar.visibility = View.VISIBLE

                is Resource.Failure -> {
                    Toast.makeText(activity, " failure", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                }

            }
        })

    }

    override fun clicked(movie: Movie, position: Int) {
        Toast.makeText(activity, " clicke: $position", Toast.LENGTH_SHORT).show()
    }
}