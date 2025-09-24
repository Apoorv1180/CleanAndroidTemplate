package com.androidcleantemplate.core.presentation.ui

import com.androidcleantemplate.core.domain.DataError
import com.androidcleantemplate.core.presentation.ui.UiText

/**
 * DataErrorToUiText - Essential utility for converting DataError to user-friendly text.
 * 
 * This provides a comprehensive mapping from our superior DataError types
 * to user-friendly UiText for display in the UI.
 * 
 * Usage:
 * ```kotlin
 * val result = repository.getData()
 * if (result is Result.Error) {
 *     val errorText = result.error.asUiText()
 *     // Display errorText in UI
 * }
 * ```
 */
fun DataError.asUiText(): UiText {
    return when(this) {
        // Network Errors
        DataError.Network.UNKNOWN -> UiText.StringResource(R.string.error_network_unknown)
        DataError.Network.TIMEOUT -> UiText.StringResource(R.string.error_network_timeout)
        DataError.Network.CONNECTION_ERROR -> UiText.StringResource(R.string.error_network_connection)
        DataError.Network.SERIALIZATION -> UiText.StringResource(R.string.error_network_serialization)
        is DataError.Network.SERVER_ERROR -> UiText.StringResource(R.string.error_network_server_error)
        DataError.Network.UNAUTHORIZED -> UiText.StringResource(R.string.error_network_unauthorized)
        DataError.Network.BAD_REQUEST -> UiText.StringResource(R.string.error_network_bad_request)
        DataError.Network.FORBIDDEN -> UiText.StringResource(R.string.error_network_forbidden)
        DataError.Network.NOT_FOUND -> UiText.StringResource(R.string.error_network_not_found)
        DataError.Network.CONFLICT -> UiText.StringResource(R.string.error_network_conflict)
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.error_network_too_many_requests)
        DataError.Network.NO_INTERNET -> UiText.StringResource(R.string.error_network_no_internet)
        
        // Local Errors
        DataError.Local.UNKNOWN -> UiText.StringResource(R.string.error_local_unknown)
        DataError.Local.DATABASE_ERROR -> UiText.StringResource(R.string.error_local_database)
        DataError.Local.STORAGE_ERROR -> UiText.StringResource(R.string.error_local_storage)
        DataError.Local.CACHE_ERROR -> UiText.StringResource(R.string.error_local_cache)
        DataError.Local.NOT_FOUND -> UiText.StringResource(R.string.error_local_not_found)
        DataError.Local.INVALID_DATA -> UiText.StringResource(R.string.error_local_invalid_data)
        
        // Validation Errors
        DataError.Validation.UNKNOWN -> UiText.StringResource(R.string.error_validation_unknown)
        DataError.Validation.INVALID_INPUT -> UiText.StringResource(R.string.error_validation_invalid_input)
        DataError.Validation.MISSING_REQUIRED_FIELD -> UiText.StringResource(R.string.error_validation_missing_field)
        DataError.Validation.INVALID_FORMAT -> UiText.StringResource(R.string.error_validation_invalid_format)
        DataError.Validation.TOO_SHORT -> UiText.StringResource(R.string.error_validation_too_short)
        DataError.Validation.TOO_LONG -> UiText.StringResource(R.string.error_validation_too_long)
        DataError.Validation.DOES_NOT_MATCH -> UiText.StringResource(R.string.error_validation_does_not_match)
        
        // Auth Errors
        DataError.Auth.UNKNOWN -> UiText.StringResource(R.string.error_auth_unknown)
        DataError.Auth.INVALID_CREDENTIALS -> UiText.StringResource(R.string.error_auth_invalid_credentials)
        DataError.Auth.SESSION_EXPIRED -> UiText.StringResource(R.string.error_auth_session_expired)
        DataError.Auth.USER_NOT_FOUND -> UiText.StringResource(R.string.error_auth_user_not_found)
        DataError.Auth.ACCOUNT_LOCKED -> UiText.StringResource(R.string.error_auth_account_locked)
        
        // App Errors
        DataError.App.UNKNOWN -> UiText.StringResource(R.string.error_app_unknown)
        DataError.App.FEATURE_NOT_AVAILABLE -> UiText.StringResource(R.string.error_app_feature_not_available)
        DataError.App.PERMISSION_DENIED -> UiText.StringResource(R.string.error_app_permission_denied)
    }
}
