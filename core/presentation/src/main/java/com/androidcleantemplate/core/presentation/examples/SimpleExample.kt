package com.androidcleantemplate.core.presentation.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidcleantemplate.core.presentation.SimpleViewModel
import com.androidcleantemplate.core.presentation.ui.ObserveAsEvents
import com.androidcleantemplate.core.presentation.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * SIMPLE EXAMPLE - Easy to understand and use!
 * 
 * This demonstrates our simplified approach that's easy to understand
 * and maintain, while being compatible with both RUNIQUE and Screenly.
 */

// ===== 1. SIMPLE STATE =====
data class SimpleMovieState(
    val isLoading: Boolean = false,
    val movies: List<String> = emptyList(),
    val error: UiText? = null
)

// ===== 2. SIMPLE ACTIONS =====
sealed interface SimpleMovieAction {
    object LoadMovies : SimpleMovieAction
    object Retry : SimpleMovieAction
}

// ===== 3. SIMPLE EVENTS =====
sealed interface SimpleMovieEvent {
    data class ShowToast(val message: UiText) : SimpleMovieEvent
}

// ===== 4. SIMPLE VIEWMODEL =====
@HiltViewModel
class SimpleMovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : SimpleViewModel<SimpleMovieState>() {
    
    override fun createInitialState(): SimpleMovieState = SimpleMovieState()
    
    override fun onAction(action: Any) {
        when (action) {
            SimpleMovieAction.LoadMovies -> loadMovies()
            SimpleMovieAction.Retry -> loadMovies()
        }
    }
    
    private fun loadMovies() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }
            
            val result = getMoviesUseCase()
            when (result) {
                is com.androidcleantemplate.core.domain.Result.Success -> {
                    setState { copy(
                        isLoading = false,
                        movies = result.data,
                        error = null
                    ) }
                }
                is com.androidcleantemplate.core.domain.Result.Error -> {
                    setState { copy(
                        isLoading = false,
                        error = result.error.asUiText()
                    ) }
                }
            }
        }
    }
}

// ===== 5. SIMPLE USECASE =====
class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : com.androidcleantemplate.core.domain.SimpleUseCase<List<String>>() {
    override suspend operator fun invoke(): com.androidcleantemplate.core.domain.Result<List<String>, com.androidcleantemplate.core.domain.DataError> {
        return movieRepository.getMovies()
    }
}

// ===== 6. SIMPLE REPOSITORY =====
interface MovieRepository {
    suspend fun getMovies(): com.androidcleantemplate.core.domain.Result<List<String>, com.androidcleantemplate.core.domain.DataError>
}

// ===== 7. SIMPLE UI =====
@Composable
fun SimpleMovieScreen(
    viewModel: SimpleMovieViewModel
) {
    val state by viewModel.state.collectAsState()
    
    // Handle one-time events (toasts, navigation)
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is SimpleMovieEvent.ShowToast -> {
                // Show toast with event.message
            }
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { viewModel.onAction(SimpleMovieAction.LoadMovies) }
        ) {
            Text("Load Movies")
        }
        
        if (state.isLoading) {
            Text("Loading...")
        } else {
            state.movies.forEach { movie ->
                Text(movie)
            }
        }
        
        state.error?.let { error ->
            Text(text = error.asString())
        }
    }
}

/**
 * HOW TO USE THIS TEMPLATE:
 * 
 * 1. Create your State data class
 * 2. Create your Action sealed interface
 * 3. Create your Event sealed interface
 * 4. Create your UseCase extending SimpleUseCase
 * 5. Create your ViewModel extending SimpleViewModel
 * 6. Create your UI using ObserveAsEvents
 * 
 * That's it! No complexity, no confusion, just clean, simple code.
 */
