package com.androidcleantemplate.core.networking.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * API response model for sample data.
 * 
 * This data class represents the structure of sample data
 * received from the API using kotlinx.serialization.
 * 
 * @param id Unique identifier
 * @param name Name of the sample
 * @param description Description of the sample
 * @param createdAt Timestamp when the item was created
 */
@Serializable
data class SampleApiResponse(
    val id: String,
    val name: String,
    val description: String,
    @SerialName("created_at")
    val createdAt: Long
)
