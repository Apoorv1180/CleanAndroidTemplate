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
 * SIMPLIFIED ViewModel approach - Easy to understand and use!
 * 
 * This provides a single, simple pattern that works for all scenarios.
 * No need to choose between different approaches - just use this one!
 */

/**
 * Simple ViewModel that everyone can understand and use.
 * 
 * This is the ONLY ViewModel class you need to know about.
 * It works for all scenarios - simple or complex.
 * 
 * Examples:
 * 
 * // Simple usage:
 * class MovieViewModel @Inject constructor(
 *     private val getMoviesUseCase: GetMoviesUseCase
 * ) : SimpleViewModel<MovieState>() {
 *     override fun createInitialState(): MovieState = MovieState()
 *     
 *     override fun onAction(action: MovieAction) {
 *         when (action) {
 *             MovieAction.LoadMovies -> loadMovies()
 *         }
 *     }
 *     
 *     private fun loadMovies() {
 *         viewModelScope.launch {
 *             setState { copy(isLoading = true) }
 *             val result = getMoviesUseCase()
 *             // Handle result...
 *         }
 *     }
 * }
 */
abstract class SimpleViewModel<State> : ViewModel() {
    private val initialState: State by lazy { createInitialState() }
    
    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()
    
    private val _events = Channel<Any>(Channel.UNLIMITED)
    val events = _events.receiveAsFlow()
    
    /**
     * Creates the initial state for the ViewModel.
     */
    protected abstract fun createInitialState(): State
    
    /**
     * Handles user actions.
     * 
     * @param action The action to handle
     */
    abstract fun onAction(action: Any)
    
    /**
     * Updates the current state.
     * 
     * @param reducer Function that transforms current state to new state
     */
    protected fun setState(reducer: State.() -> State) {
        _state.update(reducer)
    }
    
    /**
     * Emits a one-time event (like showing a toast).
     * 
     * @param event The event to emit
     */
    protected fun emitEvent(event: Any) {
        viewModelScope.launch {
            _events.send(event)
        }
    }
}
