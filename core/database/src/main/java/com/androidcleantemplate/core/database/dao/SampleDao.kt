package com.androidcleantemplate.core.database.dao

import androidx.room.*
import com.androidcleantemplate.core.database.entity.SampleEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for sample entities.
 * 
 * This DAO provides methods to interact with sample entities
 * in the local database using Room.
 */
@Dao
interface SampleDao {

    /**
     * Gets all sample entities as a Flow for reactive updates.
     * 
     * @return Flow of list of sample entities
     */
    @Query("SELECT * FROM sample_entities ORDER BY createdAt DESC")
    fun getAllSamples(): Flow<List<SampleEntity>>

    /**
     * Gets a specific sample entity by ID.
     * 
     * @param id The ID of the sample entity
     * @return Flow of the sample entity or null if not found
     */
    @Query("SELECT * FROM sample_entities WHERE id = :id")
    fun getSampleById(id: String): Flow<SampleEntity?>

    /**
     * Inserts a new sample entity.
     * 
     * @param sample The sample entity to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSample(sample: SampleEntity)

    /**
     * Updates an existing sample entity.
     * 
     * @param sample The sample entity to update
     */
    @Update
    suspend fun updateSample(sample: SampleEntity)

    /**
     * Deletes a sample entity by ID.
     * 
     * @param id The ID of the sample entity to delete
     */
    @Query("DELETE FROM sample_entities WHERE id = :id")
    suspend fun deleteSampleById(id: String)

    /**
     * Deletes all sample entities.
     */
    @Query("DELETE FROM sample_entities")
    suspend fun deleteAllSamples()
}
