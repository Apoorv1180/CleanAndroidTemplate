package com.androidcleantemplate.core.data

import com.androidcleantemplate.core.data.mapper.SampleMapper
import com.androidcleantemplate.core.data.repository.SampleRepositoryImpl
import com.androidcleantemplate.core.database.dao.SampleDao
import com.androidcleantemplate.core.domain.Sample
import com.androidcleantemplate.core.domain.SampleRepository
import com.androidcleantemplate.core.networking.api.SampleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Data module for dependency injection.
 * 
 * This module provides data layer dependencies including
 * repositories, mappers, and data sources using Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /**
     * Provides SampleMapper for converting between data models.
     * 
     * @return SampleMapper instance
     */
    @Provides
    @Singleton
    fun provideSampleMapper(): SampleMapper {
        return SampleMapper()
    }

    /**
     * Provides SampleRepository implementation.
     * 
     * @param sampleDao Database access object
     * @param sampleApiService API service
     * @param sampleMapper Mapper for data conversion
     * @return SampleRepository implementation
     */
    @Provides
    @Singleton
    fun provideSampleRepository(
        sampleDao: SampleDao,
        sampleApiService: SampleApiService,
        sampleMapper: SampleMapper
    ): SampleRepository {
        return SampleRepositoryImpl(
            sampleDao = sampleDao,
            sampleApiService = sampleApiService,
            sampleMapper = sampleMapper
        )
    }
}
