package com.androidcleantemplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for Android Clean Template.
 * 
 * This class is annotated with @HiltAndroidApp to enable Hilt dependency injection
 * throughout the application. Hilt will generate the necessary code for dependency
 * injection based on the modules and components defined in the project.
 * 
 * @see HiltAndroidApp
 */
@HiltAndroidApp
class CleanAndroidTemplateApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        // Initialize any application-level components here
        // This is called when the application process is created
    }
}
