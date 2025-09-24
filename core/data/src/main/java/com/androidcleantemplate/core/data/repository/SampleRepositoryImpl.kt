package com.androidcleantemplate.core.data.repository

import com.androidcleantemplate.core.data.mapper.SampleMapper
import com.androidcleantemplate.core.database.dao.SampleDao
import com.androidcleantemplate.core.domain.DataError
import com.androidcleantemplate.core.domain.Result
import com.androidcleantemplate.core.domain.Sample
import com.androidcleantemplate.core.domain.SampleRepository
import com.androidcleantemplate.core.networking.api.SampleApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of SampleRepository following Clean Architecture.
 * 
 * This repository implements the Single Source of Truth (SSOT) pattern
 * by combining local database and remote API data sources.
 */
@Singleton
class SampleRepositoryImpl @Inject constructor(
    private val sampleDao: SampleDao,
    private val sampleApiService: SampleApiService,
    private val sampleMapper: SampleMapper
) : SampleRepository {

    /**
     * Gets all samples with offline-first approach.
     * 
     * @return Flow of Result containing list of samples
     */
    override fun getAllSamples(): Flow<Result<List<Sample>, DataError>> {
        return sampleDao.getAllSamples().map { entities ->
            Result.Success(sampleMapper.mapEntityListToDomain(entities))
        }
    }

    /**
     * Gets a specific sample by ID.
     * 
     * @param id The ID of the sample
     * @return Flow of Result containing the sample
     */
    override fun getSampleById(id: String): Flow<Result<Sample?, DataError>> {
        return sampleDao.getSampleById(id).map { entity ->
            val domain = entity?.let { sampleMapper.mapEntityToDomain(it) }
            Result.Success(domain)
        }
    }

    /**
     * Refreshes sample data from the API.
     * 
     * @return Result indicating success or failure
     */
    override suspend fun refreshSamples(): Result<Unit, DataError> {
        return try {
            val response = sampleApiService.getAllSamples()
            if (response.isSuccessful) {
                val apiResponses = response.body() ?: emptyList()
                val samples = sampleMapper.mapApiResponseListToDomain(apiResponses)
                
                // Update local database
                samples.forEach { sample ->
                    val entity = sampleMapper.mapDomainToEntity(sample)
                    sampleDao.insertSample(entity)
                }
                
                Result.Success(Unit)
            } else {
                Result.Error(DataError.Network.SERVER_ERROR(response.code()))
            }
        } catch (e: HttpException) {
            Result.Error(mapHttpExceptionToDataError(e))
        } catch (e: IOException) {
            Result.Error(DataError.Network.CONNECTION_ERROR)
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

    /**
     * Maps HTTP exceptions to appropriate DataError types.
     * 
     * @param exception HTTP exception
     * @return Corresponding DataError
     */
    private fun mapHttpExceptionToDataError(exception: HttpException): DataError {
        return when (exception.code()) {
            400 -> DataError.Network.BAD_REQUEST
            401 -> DataError.Network.UNAUTHORIZED
            403 -> DataError.Network.FORBIDDEN
            404 -> DataError.Network.NOT_FOUND
            409 -> DataError.Network.CONFLICT
            429 -> DataError.Network.TOO_MANY_REQUESTS
            in 500..599 -> DataError.Network.SERVER_ERROR(exception.code())
            else -> DataError.Network.UNKNOWN
        }
    }
}
