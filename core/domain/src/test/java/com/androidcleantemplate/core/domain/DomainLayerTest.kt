package com.androidcleantemplate.core.domain

import org.junit.Test
import org.junit.Assert.*

/**
 * Simple test to verify the domain layer is working correctly.
 */
class DomainLayerTest {

    @Test
    fun `test Sample domain model creation`() {
        // Given
        val id = "test-id"
        val name = "Test Sample"
        val description = "Test Description"
        val createdAt = System.currentTimeMillis()

        // When
        val sample = Sample(
            id = id,
            name = name,
            description = description,
            createdAt = createdAt
        )

        // Then
        assertEquals(id, sample.id)
        assertEquals(name, sample.name)
        assertEquals(description, sample.description)
        assertEquals(createdAt, sample.createdAt)
    }

    @Test
    fun `test Result Success creation`() {
        // Given
        val testData = "test data"

        // When
        val result = Result.Success(testData)

        // Then
        assertTrue(result is Result.Success)
        assertEquals(testData, result.data)
    }

    @Test
    fun `test Result Error creation`() {
        // Given
        val error = DataError.Network.UNKNOWN

        // When
        val result = Result.Error(error)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(error, result.error)
    }
}
