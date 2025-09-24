package com.androidcleantemplate.core.domain.examples

import com.androidcleantemplate.core.domain.CompatibilityUseCase
import com.androidcleantemplate.core.domain.DataError
import com.androidcleantemplate.core.domain.Result
import javax.inject.Inject

/**
 * Example implementations demonstrating like-to-like compatibility with RUNIQUE and Screenly.
 */

// ===== RUNIQUE-STYLE EXAMPLES =====

/**
 * RUNIQUE-style simple UseCase example.
 * 
 * This demonstrates compatibility with RUNIQUE's approach where UseCases
 * directly call repository methods without complex parameter handling.
 */
class GetAnalyticsUseCase @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : CompatibilityUseCase.SimpleUseCase<AnalyticsValues>() {
    override suspend operator fun invoke(): Result<AnalyticsValues, DataError> {
        return try {
            val analyticsValues = analyticsRepository.getAnalyticsValues()
            Result.Success(analyticsValues)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DATABASE_ERROR)
        }
    }
}

// ===== SCREENLY-STYLE EXAMPLES =====

/**
 * Screenly-style UseCase with input parameters example.
 * 
 * This demonstrates compatibility with Screenly's approach where UseCases
 * accept input parameters and return Results.
 */
class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : CompatibilityUseCase.ParameterizedUseCase<List<Movie>, Int>() {
    override suspend operator fun invoke(input: Int): Result<List<Movie>, DataError> {
        return movieRepository.getPopularMovies(input)
    }
}

/**
 * Screenly-style UseCase without parameters example.
 */
class GetGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : CompatibilityUseCase.SimpleUseCase<List<Genre>>() {
    override suspend operator fun invoke(): Result<List<Genre>, DataError> {
        return movieRepository.getGenres()
    }
}

// ===== HYBRID-STYLE EXAMPLES =====

/**
 * Hybrid UseCase that supports both patterns with default parameters.
 * 
 * This demonstrates our enhanced approach that maintains compatibility
 * with both RUNIQUE and Screenly while providing superior functionality.
 */
class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : CompatibilityUseCase.HybridUseCase<List<Movie>, Int>() {
    override suspend operator fun invoke(input: Int = 1): Result<List<Movie>, DataError> {
        return movieRepository.getPopularMovies(input)
    }
}

// ===== MOCK INTERFACES FOR EXAMPLES =====

interface AnalyticsRepository {
    suspend fun getAnalyticsValues(): AnalyticsValues
}

interface MovieRepository {
    suspend fun getPopularMovies(page: Int = 1): Result<List<Movie>, DataError>
    suspend fun getGenres(): Result<List<Genre>, DataError>
}

// ===== MOCK DATA CLASSES =====

data class AnalyticsValues(
    val totalDistanceRun: Double,
    val totalTimeRun: Long,
    val fastestEverRun: Double,
    val avgDistancePerRun: Double,
    val avgPacePerRun: Double
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double
)

data class Genre(
    val id: Int,
    val name: String
)
