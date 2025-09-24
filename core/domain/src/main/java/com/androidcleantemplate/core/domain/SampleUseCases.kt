package com.androidcleantemplate.core.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UseCase for getting all samples.
 * 
 * This UseCase demonstrates the complete data flow from
 * repository to domain layer using our simplified approach.
 */
class GetAllSamplesUseCase @Inject constructor(
    private val repository: SampleRepository
) : SimpleUseCase<Flow<Result<List<Sample>, DataError>>>() {

    override suspend operator fun invoke(): Result<Flow<Result<List<Sample>, DataError>>, DataError> {
        return try {
            val flow = repository.getAllSamples()
            Result.Success(flow)
        } catch (e: Exception) {
            Result.Error(DataError.App.UNKNOWN)
        }
    }
}

/**
 * UseCase for refreshing samples from API.
 * 
 * This UseCase demonstrates offline-first data synchronization.
 */
class RefreshSamplesUseCase @Inject constructor(
    private val repository: SampleRepository
) : SimpleUseCase<Unit>() {

    override suspend operator fun invoke(): Result<Unit, DataError> {
        return repository.refreshSamples()
    }
}

/**
 * UseCase for getting a specific sample by ID.
 * 
 * This UseCase demonstrates single item retrieval.
 */
class GetSampleByIdUseCase @Inject constructor(
    private val repository: SampleRepository
) : SimpleUseCase<Flow<Result<Sample?, DataError>>, String>() {

    override suspend operator fun invoke(input: String): Result<Flow<Result<Sample?, DataError>>, DataError> {
        return try {
            val flow = repository.getSampleById(input)
            Result.Success(flow)
        } catch (e: Exception) {
            Result.Error(DataError.App.UNKNOWN)
        }
    }
}
