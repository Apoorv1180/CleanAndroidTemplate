package com.androidcleantemplate.core.presentation.examples

import androidx.lifecycle.viewModelScope
import com.androidcleantemplate.core.domain.DataError
import com.androidcleantemplate.core.domain.Result
import com.androidcleantemplate.core.presentation.CompatibilityViewModel
import com.androidcleantemplate.core.presentation.examples.CompatibilityExamples.AnalyticsValues
import com.androidcleantemplate.core.presentation.examples.CompatibilityExamples.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Example ViewModel implementations demonstrating like-to-like compatibility with RUNIQUE and Screenly.
 */

// ===== RUNIQUE-STYLE EXAMPLES =====

/**
 * RUNIQUE-style simple ViewModel example.
 * 
 * This demonstrates compatibility with RUNIQUE's approach where ViewModels
 * directly inject repositories and call them in init blocks.
 */
@HiltViewModel
class AnalyticsDashboardViewModel @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : CompatibilityViewModel.SimpleViewModel<AnalyticsDashboardState>() {
    
    init {
        viewModelScope.launch {
            val result = analyticsRepository.getAnalyticsValues()
            if (result is Result.Success) {
                setState(result.data.toAnalyticsDashboardState())
            }
        }
    }
}

// ===== SCREENLY-STYLE EXAMPLES =====

/**
 * Screenly-style MVI ViewModel example.
 * 
 * This demonstrates compatibility with Screenly's MVI approach with proper
 * Action/State/Event pattern.
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : CompatibilityViewModel.MviViewModel<MovieListState, MovieListAction, MovieListEvent>() {
    
    override fun createInitialState(): MovieListState = MovieListState()
    
    override fun onAction(action: MovieListAction) {
        when (action) {
            MovieListAction.LoadMovies -> loadMovies()
            MovieListAction.LoadGenres -> loadGenres()
            is MovieListAction.SearchMovies -> searchMovies(action.query)
            MovieListAction.RetryLoadMovies -> loadMovies()
        }
    }
    
    private fun loadMovies() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }
            
            when (val result = getPopularMoviesUseCase(1)) {
                is Result.Success -> {
                    setState { copy(
                        isLoading = false,
                        movies = result.data,
                        error = null
                    ) }
                }
                is Result.Error -> {
                    setState { copy(
                        isLoading = false,
                        error = result.error
                    ) }
                }
            }
        }
    }
    
    private fun loadGenres() {
        viewModelScope.launch {
            setState { copy(isLoadingGenres = true, genresError = null) }
            
            when (val result = getGenresUseCase()) {
                is Result.Success -> {
                    setState { copy(
                        isLoadingGenres = false,
                        genres = result.data,
                        genresError = null
                    ) }
                }
                is Result.Error -> {
                    setState { copy(
                        isLoadingGenres = false,
                        genresError = result.error
                    ) }
                }
            }
        }
    }
    
    private fun searchMovies(query: String) {
        viewModelScope.launch {
            setState { copy(searchQuery = query, isLoading = true) }
            
            // Simulate search logic
            val filteredMovies = state.value.movies.filter { 
                it.title.contains(query, ignoreCase = true) 
            }
            
            setState { copy(
                searchResults = filteredMovies,
                isLoading = false
            ) }
        }
    }
}

// ===== HYBRID-STYLE EXAMPLES =====

/**
 * Hybrid ViewModel that supports both patterns.
 * 
 * This demonstrates our enhanced approach that maintains compatibility
 * with both RUNIQUE and Screenly while providing superior functionality.
 */
@HiltViewModel
class HybridMovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val movieRepository: MovieRepository
) : CompatibilityViewModel.HybridViewModel<MovieState, MovieAction, MovieEvent>() {
    
    override fun createInitialState(): MovieState = MovieState()
    
    override fun onAction(action: MovieAction) {
        when (action) {
            MovieAction.LoadMovies -> loadMovies()
            is MovieAction.ToggleFavorite -> toggleFavorite(action.movie)
        }
    }
    
    // Can use both patterns - UseCase pattern (Screenly style)
    private fun loadMovies() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            
            when (val result = getMoviesUseCase(1)) {
                is Result.Success -> {
                    setState { copy(
                        isLoading = false,
                        movies = result.data
                    ) }
                }
                is Result.Error -> {
                    setState { copy(
                        isLoading = false,
                        error = result.error
                    ) }
                }
            }
        }
    }
    
    // Can also use direct repository calls (RUNIQUE style)
    init {
        viewModelScope.launch {
            // Direct repository call for initial load
            val result = movieRepository.getPopularMovies(1)
            if (result is Result.Success) {
                setState { copy(movies = result.data) }
            }
        }
    }
    
    private fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            // Simulate favorite toggle
            emitEvent(MovieEvent.ShowToast("Toggled favorite for ${movie.title}"))
        }
    }
}

// ===== STATE CLASSES =====

data class AnalyticsDashboardState(
    val totalDistanceRun: String,
    val totalTimeRun: String,
    val fastestEverRun: String,
    val avgDistance: String,
    val avgPace: String
)

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val searchResults: List<Movie> = emptyList(),
    val searchQuery: String = "",
    val isLoadingGenres: Boolean = false,
    val error: DataError? = null,
    val genresError: DataError? = null
)

data class MovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: DataError? = null
)

// ===== ACTION CLASSES =====

sealed interface MovieListAction {
    object LoadMovies : MovieListAction
    object LoadGenres : MovieListAction
    data class SearchMovies(val query: String) : MovieListAction
    object RetryLoadMovies : MovieListAction
}

sealed interface MovieAction {
    object LoadMovies : MovieAction
    data class ToggleFavorite(val movie: Movie) : MovieAction
}

// ===== EVENT CLASSES =====

sealed interface MovieListEvent {
    data class ShowToast(val message: String) : MovieListEvent
}

sealed interface MovieEvent {
    data class ShowToast(val message: String) : MovieEvent
}

// ===== MOCK INTERFACES =====

interface AnalyticsRepository {
    suspend fun getAnalyticsValues(): Result<AnalyticsValues, DataError>
}

interface MovieRepository {
    suspend fun getPopularMovies(page: Int = 1): Result<List<Movie>, DataError>
}

// ===== MOCK DATA CLASSES =====

data class Genre(
    val id: Int,
    val name: String
)

// ===== EXTENSION FUNCTIONS =====

fun AnalyticsValues.toAnalyticsDashboardState(): AnalyticsDashboardState {
    return AnalyticsDashboardState(
        totalDistanceRun = "${totalDistanceRun}km",
        totalTimeRun = "${totalTimeRun}ms",
        fastestEverRun = "${fastestEverRun}km/h",
        avgDistance = "${avgDistancePerRun}km",
        avgPace = "${avgPacePerRun}min/km"
    )
}
