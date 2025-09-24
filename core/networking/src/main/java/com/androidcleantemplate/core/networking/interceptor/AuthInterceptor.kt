package com.androidcleantemplate.core.networking.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Authentication interceptor for adding auth headers to requests.
 * 
 * This interceptor automatically adds authentication headers
 * to outgoing HTTP requests.
 */
@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // Add authentication headers here
        val authenticatedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer YOUR_TOKEN_HERE") // Replace with actual token
            .addHeader("Content-Type", "application/json")
            .build()
        
        return chain.proceed(authenticatedRequest)
    }
}
