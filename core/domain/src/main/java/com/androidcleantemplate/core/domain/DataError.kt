package com.androidcleantemplate.core.domain

/**
 * Sealed class representing different types of data errors.
 * 
 * This follows the comprehensive checklist requirement for comprehensive error handling
 * and provides a structured way to handle various error scenarios in the application.
 */
sealed class DataError {
    /**
     * Network-related errors.
     */
    sealed class Network : DataError() {
        object NoInternetConnection : Network()
        object Timeout : Network()
        object ServerError : Network()
        object Unauthorized : Network()
        object Forbidden : Network()
        object NotFound : Network()
        data class Unknown(val message: String) : Network()
    }
    
    /**
     * Database-related errors.
     */
    sealed class Database : DataError() {
        object DatabaseCorrupted : Database()
        object DatabaseLocked : Database()
        object ConstraintViolation : Database()
        object DataNotFound : Database()
        data class Unknown(val message: String) : Database()
    }
    
    /**
     * Local storage errors.
     */
    sealed class Local : DataError() {
        object StorageFull : Local()
        object PermissionDenied : Local()
        object FileNotFound : Local()
        object CorruptedData : Local()
        data class Unknown(val message: String) : Local()
    }
    
    /**
     * Business logic errors.
     */
    sealed class Business : DataError() {
        object InvalidInput : Business()
        object ValidationFailed : Business()
        object OperationNotAllowed : Business()
        object ResourceNotFound : Business()
        data class Custom(val message: String) : Business()
    }
    
    /**
     * Generic unknown error.
     */
    data class Unknown(val message: String) : DataError()
    
    /**
     * Returns a user-friendly error message.
     */
    fun getMessage(): String = when (this) {
        is Network.NoInternetConnection -> "No internet connection available"
        is Network.Timeout -> "Request timed out"
        is Network.ServerError -> "Server error occurred"
        is Network.Unauthorized -> "Unauthorized access"
        is Network.Forbidden -> "Access forbidden"
        is Network.NotFound -> "Resource not found"
        is Network.Unknown -> message
        
        is Database.DatabaseCorrupted -> "Database is corrupted"
        is Database.DatabaseLocked -> "Database is locked"
        is Database.ConstraintViolation -> "Database constraint violation"
        is Database.DataNotFound -> "Data not found in database"
        is Database.Unknown -> message
        
        is Local.StorageFull -> "Storage is full"
        is Local.PermissionDenied -> "Permission denied"
        is Local.FileNotFound -> "File not found"
        is Local.CorruptedData -> "Data is corrupted"
        is Local.Unknown -> message
        
        is Business.InvalidInput -> "Invalid input provided"
        is Business.ValidationFailed -> "Validation failed"
        is Business.OperationNotAllowed -> "Operation not allowed"
        is Business.ResourceNotFound -> "Resource not found"
        is Business.Custom -> message
        
        is Unknown -> message
    }
}
