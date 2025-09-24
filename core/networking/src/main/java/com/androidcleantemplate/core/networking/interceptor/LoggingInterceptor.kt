package com.androidcleantemplate.core.networking.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Logging interceptor for debugging network requests.
 * 
 * This interceptor logs HTTP requests and responses for debugging purposes.
 * It should only be used in debug builds.
 */
@Singleton
class LoggingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
        // Log request
        Timber.d("Request: ${request.method} ${request.url}")
        Timber.d("Headers: ${request.headers}")
        
        val response = chain.proceed(request)
        
        // Log response
        Timber.d("Response: ${response.code} ${response.message}")
        Timber.d("Response Headers: ${response.headers}")
        
        return response
    }
}
