package com.androidcleantemplate.core.networking.api

import com.androidcleantemplate.core.networking.model.SampleApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API service interface for sample data operations.
 * 
 * This interface defines the API endpoints for sample data
 * using Retrofit annotations.
 */
interface SampleApiService {

    /**
     * Gets all samples from the API.
     * 
     * @return Response containing list of sample data
     */
    @GET("samples")
    suspend fun getAllSamples(): Response<List<SampleApiResponse>>

    /**
     * Gets a specific sample by ID from the API.
     * 
     * @param id The ID of the sample to retrieve
     * @return Response containing the sample data
     */
    @GET("samples/{id}")
    suspend fun getSampleById(@Path("id") id: String): Response<SampleApiResponse>
}
