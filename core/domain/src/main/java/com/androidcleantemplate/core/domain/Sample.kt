package com.androidcleantemplate.core.domain

/**
 * Domain model for Sample data.
 * 
 * This represents the core business entity for sample data
 * in the domain layer, independent of data sources.
 * 
 * @param id Unique identifier
 * @param name Name of the sample
 * @param description Description of the sample
 * @param createdAt Timestamp when the item was created
 */
data class Sample(
    val id: String,
    val name: String,
    val description: String,
    val createdAt: Long
)
