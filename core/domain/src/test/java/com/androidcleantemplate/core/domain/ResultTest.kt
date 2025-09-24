package com.androidcleantemplate.core.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests for the Result wrapper class.
 * 
 * This follows the comprehensive checklist requirement for comprehensive testing
 * and ensures the Result wrapper works correctly in all scenarios.
 */
class ResultTest : BaseDomainTest() {
    
    @Test
    fun `success result should return correct data`() {
        // Given
        val expectedData = "test data"
        val result = Result.success(expectedData)
        
        // When & Then
        assertTrue(result.isSuccess)
        assertFalse(result.isError)
        assertEquals(expectedData, result.getOrNull())
        assertNull(result.getErrorOrNull())
    }
    
    @Test
    fun `error result should return correct error`() {
        // Given
        val expectedError = DataError.Network.NoInternetConnection
        val result = Result.error(expectedError)
        
        // When & Then
        assertFalse(result.isSuccess)
        assertTrue(result.isError)
        assertNull(result.getOrNull())
        assertEquals(expectedError, result.getErrorOrNull())
    }
    
    @Test
    fun `map should transform success data`() {
        // Given
        val originalData = 5
        val result = Result.success(originalData)
        
        // When
        val mappedResult = result.map { it * 2 }
        
        // Then
        assertTrue(mappedResult.isSuccess)
        assertEquals(10, mappedResult.getOrNull())
    }
    
    @Test
    fun `map should preserve error`() {
        // Given
        val error = DataError.Database.DatabaseCorrupted
        val result = Result.error(error)
        
        // When
        val mappedResult = result.map { it.toString() }
        
        // Then
        assertTrue(mappedResult.isError)
        assertEquals(error, mappedResult.getErrorOrNull())
    }
    
    @Test
    fun `mapError should transform error`() {
        // Given
        val originalError = DataError.Network.NoInternetConnection
        val result = Result.error(originalError)
        
        // When
        val mappedResult = result.mapError { "Network Error" }
        
        // Then
        assertTrue(mappedResult.isError)
        assertEquals("Network Error", mappedResult.getErrorOrNull())
    }
    
    @Test
    fun `mapError should preserve success`() {
        // Given
        val data = "test"
        val result = Result.success(data)
        
        // When
        val mappedResult = result.mapError { "Error" }
        
        // Then
        assertTrue(mappedResult.isSuccess)
        assertEquals(data, mappedResult.getOrNull())
    }
    
    @Test
    fun `fold should call correct function for success`() {
        // Given
        val data = "test data"
        val result = Result.success(data)
        var successCalled = false
        var errorCalled = false
        
        // When
        result.fold(
            onSuccess = { successCalled = true },
            onError = { errorCalled = true }
        )
        
        // Then
        assertTrue(successCalled)
        assertFalse(errorCalled)
    }
    
    @Test
    fun `fold should call correct function for error`() {
        // Given
        val error = DataError.Business.InvalidInput
        val result = Result.error(error)
        var successCalled = false
        var errorCalled = false
        
        // When
        result.fold(
            onSuccess = { successCalled = true },
            onError = { errorCalled = true }
        )
        
        // Then
        assertFalse(successCalled)
        assertTrue(errorCalled)
    }
}
