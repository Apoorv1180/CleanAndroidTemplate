package com.androidcleantemplate.core.domain

/**
 * Compatibility UseCase classes that support both RUNIQUE and Screenly patterns.
 * 
 * This provides like-to-like compatibility while maintaining our superior implementations.
 */

/**
 * RUNIQUE-style simple UseCase for direct repository calls.
 * 
 * This maintains compatibility with RUNIQUE's simple approach where UseCases
 * directly call repository methods without complex parameter handling.
 * 
 * Example usage (RUNIQUE style):
 * ```kotlin
 * class GetAnalyticsUseCase @Inject constructor(
 *     private val analyticsRepository: AnalyticsRepository
 * ) : SimpleUseCase<AnalyticsValues>() {
 *     override suspend operator fun invoke(): Result<AnalyticsValues, DataError> {
 *         return analyticsRepository.getAnalyticsValues()
 *     }
 * }
 * ```
 */
abstract class SimpleUseCase<Output> {
    /**
     * Executes the UseCase.
     *
     * @return A [Result] indicating success with [Output] or an [DataError].
     */
    abstract suspend operator fun invoke(): Result<Output, DataError>
}

/**
 * Screenly-style UseCase with input parameters.
 * 
 * This maintains compatibility with Screenly's approach where UseCases
 * accept input parameters and return Results.
 * 
 * Example usage (Screenly style):
 * ```kotlin
 * class GetPopularMoviesUseCase @Inject constructor(
 *     private val movieRepository: MovieRepository
 * ) : ParameterizedUseCase<List<Movie>, Int>() {
 *     override suspend operator fun invoke(input: Int): Result<List<Movie>, DataError> {
 *         return movieRepository.getPopularMovies(input)
 *     }
 * }
 * ```
 */
abstract class ParameterizedUseCase<Output, Input> {
    /**
     * Executes the UseCase with the given input.
     *
     * @param input The input parameters for the UseCase.
     * @return A [Result] indicating success with [Output] or an [DataError].
     */
    abstract suspend operator fun invoke(input: Input): Result<Output, DataError>
}

/**
 * Enhanced UseCase that supports both patterns with default parameters.
 * 
 * This provides the best of both worlds - compatibility with both RUNIQUE and Screenly
 * while maintaining our superior error handling and Result wrapper.
 * 
 * Example usage (Hybrid style):
 * ```kotlin
 * class GetMoviesUseCase @Inject constructor(
 *     private val movieRepository: MovieRepository
 * ) : HybridUseCase<List<Movie>, Int>() {
 *     override suspend operator fun invoke(input: Int = 1): Result<List<Movie>, DataError> {
 *         return movieRepository.getPopularMovies(input)
 *     }
 * }
 * ```
 */
abstract class HybridUseCase<Output, Input> {
    /**
     * Executes the UseCase with optional input parameters.
     *
     * @param input The input parameters for the UseCase (can have default values).
     * @return A [Result] indicating success with [Output] or an [DataError].
     */
    abstract suspend operator fun invoke(input: Input): Result<Output, DataError>
}
