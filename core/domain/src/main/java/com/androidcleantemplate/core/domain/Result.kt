package com.androidcleantemplate.core.domain

/**
 * Result wrapper for handling success and error states in a type-safe manner.
 * 
 * This follows the comprehensive checklist requirement for proper error handling
 * and provides a clean way to handle both success and failure cases without exceptions.
 * 
 * @param T The type of the successful result
 * @param E The type of the error (typically DataError)
 */
sealed class Result<out T, out E> {
    /**
     * Represents a successful result containing a value.
     */
    data class Success<out T>(val data: T) : Result<T, Nothing>()
    
    /**
     * Represents a failed result containing an error.
     */
    data class Error<out E>(val error: E) : Result<Nothing, E>()
    
    /**
     * Returns true if this is a Success result.
     */
    val isSuccess: Boolean get() = this is Success
    
    /**
     * Returns true if this is an Error result.
     */
    val isError: Boolean get() = this is Error
    
    /**
     * Returns the data if this is a Success, null otherwise.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Error -> null
    }
    
    /**
     * Returns the error if this is an Error, null otherwise.
     */
    fun getErrorOrNull(): E? = when (this) {
        is Success -> null
        is Error -> error
    }
    
    /**
     * Maps the success value using the provided function.
     */
    inline fun <R> map(transform: (T) -> R): Result<R, E> = when (this) {
        is Success -> Success(transform(data))
        is Error -> Error(error)
    }
    
    /**
     * Maps the error value using the provided function.
     */
    inline fun <R> mapError(transform: (E) -> R): Result<T, R> = when (this) {
        is Success -> Success(data)
        is Error -> Error(transform(error))
    }
    
    /**
     * Executes the appropriate function based on the result type.
     */
    inline fun fold(
        onSuccess: (T) -> Unit,
        onError: (E) -> Unit
    ) = when (this) {
        is Success -> onSuccess(data)
        is Error -> onError(error)
    }
}

/**
 * Convenience function to create a Success result.
 */
fun <T> Result.Companion.success(data: T): Result<T, Nothing> = Result.Success(data)

/**
 * Convenience function to create an Error result.
 */
fun <E> Result.Companion.error(error: E): Result<Nothing, E> = Result.Error(error)
