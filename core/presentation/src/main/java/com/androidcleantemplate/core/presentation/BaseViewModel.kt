package com.androidcleantemplate.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel class following the MVI pattern.
 * 
 * This follows the comprehensive checklist requirement for MVI pattern implementation
 * with proper state management, actions, and effects handling.
 * 
 * @param STATE The type of the UI state
 * @param ACTION The type of the user actions
 * @param EFFECT The type of the side effects
 */
abstract class BaseViewModel<STATE, ACTION, EFFECT> : ViewModel() {
    
    /**
     * The current UI state.
     */
    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<STATE> = _state.asStateFlow()
    
    /**
     * Channel for one-time effects (like navigation, showing snackbars).
     * This follows the checklist requirement for ObserveAsEvents pattern.
     */
    private val _effects = Channel<EFFECT>(Channel.UNLIMITED)
    val effects: Flow<EFFECT> = _effects.receiveAsFlow()
    
    /**
     * Returns the initial state for this ViewModel.
     */
    protected abstract fun initialState(): STATE
    
    /**
     * Handles user actions and updates the state accordingly.
     * This follows the checklist requirement for unidirectional data flow.
     * 
     * @param action The action to handle
     */
    protected abstract fun handleAction(action: ACTION)
    
    /**
     * Processes a user action.
     * 
     * @param action The action to process
     */
    fun processAction(action: ACTION) {
        viewModelScope.launch {
            handleAction(action)
        }
    }
    
    /**
     * Updates the current state.
     * 
     * @param update The function to update the state
     */
    protected fun updateState(update: STATE.() -> STATE) {
        _state.value = _state.value.update()
    }
    
    /**
     * Sends a one-time effect.
     * 
     * @param effect The effect to send
     */
    protected fun sendEffect(effect: EFFECT) {
        viewModelScope.launch {
            _effects.send(effect)
        }
    }
    
    /**
     * Gets the current state value.
     */
    protected fun getCurrentState(): STATE = _state.value
}
