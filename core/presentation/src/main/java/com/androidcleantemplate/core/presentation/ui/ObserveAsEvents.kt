package com.androidcleantemplate.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * ObserveAsEvents - Essential utility for handling one-time events in Compose.
 * 
 * This uses RUNIQUE's advanced implementation with lifecycle awareness,
 * which is superior to Screenly's basic version.
 * 
 * Usage:
 * ```kotlin
 * @Composable
 * fun MovieScreen(viewModel: MovieViewModel) {
 *     val state by viewModel.state.collectAsState()
 *     
 *     // Handle one-time events like toasts, navigation
 *     ObserveAsEvents(flow = viewModel.events) { event ->
 *         when (event) {
 *             is MovieEvent.ShowToast -> {
 *                 // Show toast
 *             }
 *             is MovieEvent.NavigateToDetails -> {
 *                 // Navigate
 *             }
 *         }
 *     }
 *     
 *     // UI content
 * }
 * ```
 */
@Composable
fun <T> ObserveAsEvents(
    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle, key1, key2) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}
