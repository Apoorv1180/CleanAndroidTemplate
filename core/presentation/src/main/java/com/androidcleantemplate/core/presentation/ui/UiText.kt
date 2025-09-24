package com.androidcleantemplate.core.presentation.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

/**
 * UiText - Essential utility for handling UI text in a type-safe way.
 * 
 * This is identical to both RUNIQUE and Screenly implementations,
 * ensuring perfect compatibility with existing projects.
 * 
 * Usage:
 * ```kotlin
 * // String resource
 * val errorText = UiText.StringResource(R.string.error_message)
 * 
 * // Dynamic string
 * val dynamicText = UiText.DynamicString("Hello $name")
 * 
 * // In Compose
 * Text(text = errorText.asString())
 * ```
 */
sealed interface UiText {
    data class DynamicString(val value: String): UiText
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ): UiText

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = id, *args)
        }
    }

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}
