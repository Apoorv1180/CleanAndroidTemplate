package com.androidcleantemplate.core.networking

import com.androidcleantemplate.core.networking.api.SampleApiService
import com.androidcleantemplate.core.networking.interceptor.AuthInterceptor
import com.androidcleantemplate.core.networking.interceptor.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Networking module for dependency injection.
 * 
 * This module provides networking-related dependencies including
 * OkHttpClient, Retrofit, and API services using Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    /**
     * Provides the base URL for API requests.
     * 
     * @return Base URL string
     */
    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return "https://api.example.com/" // Replace with your actual API base URL
    }

    /**
     * Provides OkHttpClient with interceptors for logging and authentication.
     * 
     * @param authInterceptor Authentication interceptor
     * @param loggingInterceptor Logging interceptor
     * @return Configured OkHttpClient
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: LoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides Retrofit instance for API calls.
     * 
     * @param okHttpClient Configured OkHttpClient
     * @param baseUrl Base URL for API requests
     * @return Configured Retrofit instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    /**
     * Provides SampleApiService for API calls.
     * 
     * @param retrofit Configured Retrofit instance
     * @return SampleApiService instance
     */
    @Provides
    @Singleton
    fun provideSampleApiService(retrofit: Retrofit): SampleApiService {
        return retrofit.create(SampleApiService::class.java)
    }
}
