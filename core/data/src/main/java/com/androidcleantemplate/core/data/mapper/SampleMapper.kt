package com.androidcleantemplate.core.data.mapper

import com.androidcleantemplate.core.database.entity.SampleEntity
import com.androidcleantemplate.core.domain.Sample
import com.androidcleantemplate.core.networking.model.SampleApiResponse

/**
 * Mapper for converting between different data models.
 * 
 * This mapper follows the Single Responsibility Principle by
 * handling all data transformation between layers.
 */
class SampleMapper {

    /**
     * Converts API response to domain model.
     * 
     * @param apiResponse API response from network
     * @return Domain model
     */
    fun mapApiResponseToDomain(apiResponse: SampleApiResponse): Sample {
        return Sample(
            id = apiResponse.id,
            name = apiResponse.name,
            description = apiResponse.description,
            createdAt = apiResponse.createdAt
        )
    }

    /**
     * Converts domain model to database entity.
     * 
     * @param domain Domain model
     * @return Database entity
     */
    fun mapDomainToEntity(domain: Sample): SampleEntity {
        return SampleEntity(
            id = domain.id,
            name = domain.name,
            description = domain.description,
            createdAt = domain.createdAt
        )
    }

    /**
     * Converts database entity to domain model.
     * 
     * @param entity Database entity
     * @return Domain model
     */
    fun mapEntityToDomain(entity: SampleEntity): Sample {
        return Sample(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            createdAt = entity.createdAt
        )
    }

    /**
     * Converts list of API responses to domain models.
     * 
     * @param apiResponses List of API responses
     * @return List of domain models
     */
    fun mapApiResponseListToDomain(apiResponses: List<SampleApiResponse>): List<Sample> {
        return apiResponses.map { mapApiResponseToDomain(it) }
    }

    /**
     * Converts list of database entities to domain models.
     * 
     * @param entities List of database entities
     * @return List of domain models
     */
    fun mapEntityListToDomain(entities: List<SampleEntity>): List<Sample> {
        return entities.map { mapEntityToDomain(it) }
    }
}
