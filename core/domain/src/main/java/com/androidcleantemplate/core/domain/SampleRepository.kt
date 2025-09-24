package com.androidcleantemplate.core.domain

import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Sample data operations.
 * 
 * This interface defines the contract for sample data operations
 * following Clean Architecture principles.
 */
interface SampleRepository {

    /**
     * Gets all samples with offline-first approach.
     * 
     * @return Flow of Result containing list of samples
     */
    fun getAllSamples(): Flow<Result<List<Sample>, DataError>>

    /**
     * Gets a specific sample by ID.
     * 
     * @param id The ID of the sample
     * @return Flow of Result containing the sample
     */
    fun getSampleById(id: String): Flow<Result<Sample?, DataError>>

    /**
     * Refreshes sample data from the API.
     * 
     * @return Result indicating success or failure
     */
    suspend fun refreshSamples(): Result<Unit, DataError>
}
