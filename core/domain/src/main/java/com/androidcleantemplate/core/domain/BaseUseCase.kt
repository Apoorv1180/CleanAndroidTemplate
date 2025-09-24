package com.androidcleantemplate.core.domain

import kotlinx.coroutines.flow.Flow

/**
 * Base interface for all repository implementations.
 * 
 * This follows the comprehensive checklist requirement for repository interfaces
 * in the domain layer and provides a common contract for all repositories.
 */
interface BaseRepository

/**
 * Base interface for all use cases.
 * 
 * This follows the comprehensive checklist requirement for UseCase pattern
 * with single responsibility in the domain layer.
 * 
 * @param T The type of the result
 * @param P The type of the parameters
 */
interface UseCase<T, P> {
    /**
     * Executes the use case with the given parameters.
     * 
     * @param params The parameters for the use case
     * @return A Result containing either the success data or an error
     */
    suspend operator fun invoke(params: P): Result<T, DataError>
}

/**
 * Base interface for use cases that don't require parameters.
 * 
 * @param T The type of the result
 */
interface UseCaseNoParams<T> {
    /**
     * Executes the use case.
     * 
     * @return A Result containing either the success data or an error
     */
    suspend operator fun invoke(): Result<T, DataError>
}

/**
 * Base interface for use cases that return a Flow of data.
 * 
 * This follows the comprehensive checklist requirement for Flow usage
 * in the data layer for real-time updates.
 * 
 * @param T The type of the result
 * @param P The type of the parameters
 */
interface FlowUseCase<T, P> {
    /**
     * Executes the use case with the given parameters and returns a Flow.
     * 
     * @param params The parameters for the use case
     * @return A Flow of Results containing either success data or errors
     */
    operator fun invoke(params: P): Flow<Result<T, DataError>>
}

/**
 * Base interface for use cases that return a Flow and don't require parameters.
 * 
 * @param T The type of the result
 */
interface FlowUseCaseNoParams<T> {
    /**
     * Executes the use case and returns a Flow.
     * 
     * @return A Flow of Results containing either success data or errors
     */
    operator fun invoke(): Flow<Result<T, DataError>>
}

/**
 * Empty parameters class for use cases that don't require parameters.
 */
object NoParams
