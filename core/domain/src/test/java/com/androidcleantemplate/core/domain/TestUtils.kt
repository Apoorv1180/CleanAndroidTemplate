package com.androidcleantemplate.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * Test utilities for domain layer testing.
 * 
 * This follows the comprehensive checklist requirement for testing infrastructure
 * and provides common utilities for testing use cases and repositories.
 */
object TestUtils {
    
    /**
     * Creates a successful result for testing.
     */
    fun <T> successResult(data: T): Result<T, DataError> = Result.success(data)
    
    /**
     * Creates an error result for testing.
     */
    fun <T> errorResult(error: DataError): Result<T, DataError> = Result.error(error)
    
    /**
     * Creates a flow of successful results for testing.
     */
    fun <T> successFlow(data: T): Flow<Result<T, DataError>> = flowOf(Result.success(data))
    
    /**
     * Creates a flow of error results for testing.
     */
    fun <T> errorFlow(error: DataError): Flow<Result<T, DataError>> = flowOf(Result.error(error))
    
    /**
     * Creates a flow that emits multiple values for testing.
     */
    fun <T> multiValueFlow(vararg values: T): Flow<Result<T, DataError>> = flow {
        values.forEach { value ->
            emit(Result.success(value))
        }
    }
    
    /**
     * Creates a flow that emits loading, success, and error states for testing.
     */
    fun <T> stateFlow(
        loading: Boolean = false,
        data: T? = null,
        error: DataError? = null
    ): Flow<Result<T, DataError>> = flow {
        if (loading) {
            // Emit loading state (could be represented differently based on implementation)
        }
        if (error != null) {
            emit(Result.error(error))
        } else if (data != null) {
            emit(Result.success(data))
        }
    }
}

/**
 * Test data classes for common testing scenarios.
 */
object TestData {
    
    /**
     * Sample user data for testing.
     */
    data class TestUser(
        val id: String,
        val name: String,
        val email: String
    ) {
        companion object {
            val SAMPLE_USER = TestUser(
                id = "1",
                name = "Test User",
                email = "test@example.com"
            )
            
            val SAMPLE_USERS = listOf(
                SAMPLE_USER,
                TestUser("2", "Another User", "another@example.com"),
                TestUser("3", "Third User", "third@example.com")
            )
        }
    }
    
    /**
     * Sample error for testing.
     */
    object TestErrors {
        val NETWORK_ERROR = DataError.Network.NoInternetConnection
        val DATABASE_ERROR = DataError.Database.DatabaseCorrupted
        val BUSINESS_ERROR = DataError.Business.InvalidInput
        val UNKNOWN_ERROR = DataError.Unknown("Test error")
    }
}
