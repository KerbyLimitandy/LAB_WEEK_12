package com.example.test_lab_week_12

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    init {
        fetchPopularMovies()
    }

    //Define the LiveData
    val popularMovies: LiveData<List<Movie>>
        get() = movieRepository.movies

    val error: LiveData<String>
        get() = movieRepository.error

    //Fetch Movies from API
    private fun fetchPopularMovies() {
        //Launch a coroutine in viewModelScope and Dispatchers.IO means that this coroutine will run on shared pool of threads
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchMovies()
        }
    }
}