package com.androidcleantemplate.core.domain

/**
 * SIMPLIFIED UseCase approach - Easy to understand and use!
 * 
 * This provides a single, simple pattern that works for all scenarios.
 * No need to choose between different approaches - just use this one!
 */

/**
 * Simple UseCase that everyone can understand and use.
 * 
 * This is the ONLY UseCase class you need to know about.
 * It works for all scenarios - with or without parameters.
 * 
 * Examples:
 * 
 * // Without parameters:
 * class GetMoviesUseCase @Inject constructor(
 *     private val repository: MovieRepository
 * ) : SimpleUseCase<List<Movie>>() {
 *     override suspend operator fun invoke(): Result<List<Movie>, DataError> {
 *         return repository.getMovies()
 *     }
 * }
 * 
 * // With parameters:
 * class GetMovieByIdUseCase @Inject constructor(
 *     private val repository: MovieRepository
 * ) : SimpleUseCase<Movie, Int>() {
 *     override suspend operator fun invoke(input: Int): Result<Movie, DataError> {
 *         return repository.getMovieById(input)
 *     }
 * }
 */
abstract class SimpleUseCase<Output, Input = Unit> {
    /**
     * Executes the UseCase.
     * 
     * @param input The input parameters (optional - defaults to Unit)
     * @return A Result with success data or error
     */
    abstract suspend operator fun invoke(input: Input = Unit as Input): Result<Output, DataError>
}
