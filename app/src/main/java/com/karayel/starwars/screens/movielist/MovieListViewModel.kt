package com.karayel.starwars.screens.movielist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karayel.starwars.domain.DummyMovieListUseCase
import com.karayel.starwars.domain.MovieListUseCase
import com.karayel.starwars.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    // https://developer.android.com/kotlin/flow
    private val _movieListFlow = MutableStateFlow<List<Movie>>(emptyList())
    val movieListFlow = _movieListFlow.asStateFlow()

    private val _loadingStateFlow = MutableStateFlow(true)
    val loadingStateFlow = _loadingStateFlow.asStateFlow()

    init {
        /*
        Kotlin Coroutines;
        https://developer.android.com/kotlin/coroutines?gclid=Cj0KCQiA1NebBhDDARIsAANiDD2jrBPqLeoTXGeE__xpVT1AE0G9I2-ngcyo5yypXLVTY3y23ZSO29waAsQUEALw_wcB&gclsrc=aw.ds
         */
        viewModelScope.launch {
            delay(2000L)
            getMovieList()
            _loadingStateFlow.value = false
        }
    }

    private fun getMovieList() {
        /*
        Whenever it is invoked on the same object more than once,
        the hashCode method must consistently return the same integer
         */

        /*
        Solid Principles = https://www.freecodecamp.org/news/solid-principles-explained-in-plain-english/
         */
        Log.d("HashCode of Object", "${MovieListUseCase.hashCode()}")
        Log.d("HashCode of Class", "${DummyMovieListUseCase().hashCode()}")

        val movieList = MovieListUseCase.createMovieList()
        _movieListFlow.tryEmit(movieList)
    }

    fun sortMovie(direction: Direction) {
        val currentList = _movieListFlow.value

        val modifiedList = if (direction == Direction.DESC) {
            currentList.sortedByDescending { movie -> movie.date.toInt() }
        } else {
            currentList.sortedBy { movie -> movie.date.toInt() }
        }

        _movieListFlow.tryEmit(modifiedList)
    }
}

enum class Direction {
    ASC, DESC
}
