package com.androidcleantemplate.core.domain

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests for the DataError sealed class.
 * 
 * This follows the comprehensive checklist requirement for comprehensive testing
 * and ensures all error types provide appropriate messages.
 */
class DataErrorTest {
    
    @Test
    fun `network errors should have correct messages`() {
        // Given & When & Then
        assertEquals("No internet connection available", DataError.Network.NoInternetConnection.getMessage())
        assertEquals("Request timed out", DataError.Network.Timeout.getMessage())
        assertEquals("Server error occurred", DataError.Network.ServerError.getMessage())
        assertEquals("Unauthorized access", DataError.Network.Unauthorized.getMessage())
        assertEquals("Access forbidden", DataError.Network.Forbidden.getMessage())
        assertEquals("Resource not found", DataError.Network.NotFound.getMessage())
        assertEquals("Custom message", DataError.Network.Unknown("Custom message").getMessage())
    }
    
    @Test
    fun `database errors should have correct messages`() {
        // Given & When & Then
        assertEquals("Database is corrupted", DataError.Database.DatabaseCorrupted.getMessage())
        assertEquals("Database is locked", DataError.Database.DatabaseLocked.getMessage())
        assertEquals("Database constraint violation", DataError.Database.ConstraintViolation.getMessage())
        assertEquals("Data not found in database", DataError.Database.DataNotFound.getMessage())
        assertEquals("Custom DB message", DataError.Database.Unknown("Custom DB message").getMessage())
    }
    
    @Test
    fun `local errors should have correct messages`() {
        // Given & When & Then
        assertEquals("Storage is full", DataError.Local.StorageFull.getMessage())
        assertEquals("Permission denied", DataError.Local.PermissionDenied.getMessage())
        assertEquals("File not found", DataError.Local.FileNotFound.getMessage())
        assertEquals("Data is corrupted", DataError.Local.CorruptedData.getMessage())
        assertEquals("Custom local message", DataError.Local.Unknown("Custom local message").getMessage())
    }
    
    @Test
    fun `business errors should have correct messages`() {
        // Given & When & Then
        assertEquals("Invalid input provided", DataError.Business.InvalidInput.getMessage())
        assertEquals("Validation failed", DataError.Business.ValidationFailed.getMessage())
        assertEquals("Operation not allowed", DataError.Business.OperationNotAllowed.getMessage())
        assertEquals("Resource not found", DataError.Business.ResourceNotFound.getMessage())
        assertEquals("Custom business message", DataError.Business.Custom("Custom business message").getMessage())
    }
    
    @Test
    fun `unknown error should have correct message`() {
        // Given
        val error = DataError.Unknown("Something went wrong")
        
        // When & Then
        assertEquals("Something went wrong", error.getMessage())
    }
}
