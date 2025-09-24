package com.androidcleantemplate.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidcleantemplate.core.database.dao.SampleDao
import com.androidcleantemplate.core.database.entity.SampleEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

/**
 * Test for Room database functionality.
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var database: CleanAndroidTemplateDatabase
    private lateinit var sampleDao: SampleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CleanAndroidTemplateDatabase::class.java
        ).allowMainThreadQueries().build()
        sampleDao = database.sampleDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun `test insert and retrieve sample`() = runTest {
        // Given
        val sample = SampleEntity(
            id = "test-id",
            name = "Test Sample",
            description = "Test Description"
        )

        // When
        sampleDao.insertSample(sample)
        val retrievedSample = sampleDao.getSampleById("test-id").first()

        // Then
        assertNotNull(retrievedSample)
        assertEquals(sample.id, retrievedSample?.id)
        assertEquals(sample.name, retrievedSample?.name)
        assertEquals(sample.description, retrievedSample?.description)
    }

    @Test
    fun `test delete sample`() = runTest {
        // Given
        val sample = SampleEntity(
            id = "test-id",
            name = "Test Sample",
            description = "Test Description"
        )
        sampleDao.insertSample(sample)

        // When
        sampleDao.deleteSampleById("test-id")
        val retrievedSample = sampleDao.getSampleById("test-id").first()

        // Then
        assertNull(retrievedSample)
    }
}
