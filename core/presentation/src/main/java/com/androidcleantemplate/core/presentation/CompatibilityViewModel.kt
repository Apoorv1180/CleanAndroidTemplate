package com.androidcleantemplate.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Compatibility ViewModel that supports both RUNIQUE and Screenly patterns.
 * 
 * This provides like-to-like compatibility while maintaining our superior MVI implementation.
 */

/**
 * RUNIQUE-style simple ViewModel for direct repository calls.
 * 
 * This maintains compatibility with RUNIQUE's simple approach where ViewModels
 * directly inject repositories and call them in init blocks.
 * 
 * Example usage (RUNIQUE style):
 * ```kotlin
 * class AnalyticsDashboardViewModel @Inject constructor(
 *     private val analyticsRepository: AnalyticsRepository
 * ) : SimpleViewModel<AnalyticsDashboardState>() {
 *     override fun createInitialState(): AnalyticsDashboardState? = null
 *     
 *     init {
 *         viewModelScope.launch {
 *             val result = analyticsRepository.getAnalyticsValues()
 *             if (result is Result.Success) {
 *                 setState(result.data.toAnalyticsDashboardState())
 *             }
 *         }
 *     }
 * }
 * ```
 */
abstract class SimpleViewModel<State> : ViewModel() {
    private val _state = MutableStateFlow<State?>(null)
    val state: StateFlow<State?> = _state.asStateFlow()
    
    /**
     * Creates the initial state for the ViewModel.
     * Can return null for loading states (RUNIQUE style).
     */
    protected abstract fun createInitialState(): State?
    
    /**
     * Updates the current state.
     */
    protected fun setState(newState: State) {
        _state.value = newState
    }
}

/**
 * Screenly-style MVI ViewModel with Actions and Events.
 * 
 * This maintains compatibility with Screenly's MVI approach with proper
 * Action/State/Event pattern.
 * 
 * Example usage (Screenly style):
 * ```kotlin
 * class MovieListViewModel @Inject constructor(
 *     private val getPopularMoviesUseCase: GetPopularMoviesUseCase
 * ) : MviViewModel<MovieListState, MovieListAction, MovieListEvent>() {
 *     override fun createInitialState(): MovieListState = MovieListState()
 *     
 *     override fun onAction(action: MovieListAction) {
 *         when (action) {
 *             MovieListAction.LoadMovies -> loadMovies()
 *             is MovieListAction.SearchMovies -> searchMovies(action.query)
 *         }
 *     }
 *     
 *     private fun loadMovies() {
 *         viewModelScope.launch {
 *             setState { copy(isLoading = true) }
 *             val result = getPopularMoviesUseCase()
 *             // Handle result...
 *         }
 *     }
 * }
 * ```
 */
abstract class MviViewModel<State, Action, Event> : ViewModel() {
    private val initialState: State by lazy { createInitialState() }
    
    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()
    
    private val _events = Channel<Event>(Channel.UNLIMITED)
    val events = _events.receiveAsFlow()
    
    /**
     * Creates the initial UI state for the ViewModel.
     */
    protected abstract fun createInitialState(): State
    
    /**
     * Handles incoming user actions.
     *
     * @param action The action to be processed.
     */
    abstract fun onAction(action: Action)
    
    /**
     * Updates the current UI state.
     *
     * @param reducer A function that takes the current state and returns the new state.
     */
    protected fun setState(reducer: State.() -> State) {
        _state.update(reducer)
    }
    
    /**
     * Emits a one-time UI event (effect).
     *
     * @param event The event to be emitted.
     */
    protected fun emitEvent(event: Event) {
        viewModelScope.launch {
            _events.send(event)
        }
    }
}

/**
 * Hybrid ViewModel that supports both patterns.
 * 
 * This provides the best of both worlds - compatibility with both RUNIQUE and Screenly
 * while maintaining our superior MVI implementation.
 * 
 * Example usage (Hybrid style):
 * ```kotlin
 * class MovieViewModel @Inject constructor(
 *     private val getMoviesUseCase: GetMoviesUseCase
 * ) : HybridViewModel<MovieState, MovieAction, MovieEvent>() {
 *     override fun createInitialState(): MovieState = MovieState()
 *     
 *     override fun onAction(action: MovieAction) {
 *         when (action) {
 *             MovieAction.LoadMovies -> loadMovies()
 *         }
 *     }
 *     
 *     // Can also use simple repository calls like RUNIQUE
 *     init {
 *         viewModelScope.launch {
 *             // Direct repository call for initial load
 *         }
 *     }
 * }
 * ```
 */
abstract class HybridViewModel<State, Action, Event> : ViewModel() {
    private val initialState: State by lazy { createInitialState() }
    
    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()
    
    private val _events = Channel<Event>(Channel.UNLIMITED)
    val events = _events.receiveAsFlow()
    
    /**
     * Creates the initial UI state for the ViewModel.
     */
    protected abstract fun createInitialState(): State
    
    /**
     * Handles incoming user actions.
     *
     * @param action The action to be processed.
     */
    abstract fun onAction(action: Action)
    
    /**
     * Updates the current UI state.
     *
     * @param reducer A function that takes the current state and returns the new state.
     */
    protected fun setState(reducer: State.() -> State) {
        _state.update(reducer)
    }
    
    /**
     * Emits a one-time UI event (effect).
     *
     * @param event The event to be emitted.
     */
    protected fun emitEvent(event: Event) {
        viewModelScope.launch {
            _events.send(event)
        }
    }
}
