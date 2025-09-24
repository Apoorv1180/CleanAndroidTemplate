package com.androidcleantemplate.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Sample entity for demonstration purposes.
 * 
 * This entity represents a sample data model that can be stored
 * in the local database using Room.
 * 
 * @param id Unique identifier for the entity
 * @param name Name of the sample item
 * @param description Description of the sample item
 * @param createdAt Timestamp when the item was created
 */
@Entity(tableName = "sample_entities")
data class SampleEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)
