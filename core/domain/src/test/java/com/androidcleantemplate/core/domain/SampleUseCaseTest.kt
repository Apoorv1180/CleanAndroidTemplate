package com.androidcleantemplate.core.domain

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Sample use case implementation for testing demonstration.
 */
class SampleUseCase : UseCase<String, String> {
    override suspend fun invoke(params: String): Result<String, DataError> {
        return if (params.isNotEmpty()) {
            Result.success("Processed: $params")
        } else {
            Result.error(DataError.Business.InvalidInput)
        }
    }
}

/**
 * Tests for the sample use case.
 * 
 * This follows the comprehensive checklist requirement for use case testing
 * and demonstrates the proper testing patterns for domain layer.
 */
class SampleUseCaseTest : BaseDomainTest() {
    
    private val useCase = SampleUseCase()
    
    @Test
    fun `invoke with valid params should return success`() = runTest {
        // Given
        val input = "test input"
        
        // When
        val result = useCase.invoke(input)
        
        // Then
        assertTrue(result.isSuccess)
        assertEquals("Processed: $input", result.getOrNull())
    }
    
    @Test
    fun `invoke with empty params should return error`() = runTest {
        // Given
        val input = ""
        
        // When
        val result = useCase.invoke(input)
        
        // Then
        assertTrue(result.isError)
        assertEquals(DataError.Business.InvalidInput, result.getErrorOrNull())
    }
    
    @Test
    fun `invoke with whitespace params should return success`() = runTest {
        // Given
        val input = "   "
        
        // When
        val result = useCase.invoke(input)
        
        // Then
        assertTrue(result.isSuccess)
        assertEquals("Processed: $input", result.getOrNull())
    }
}
