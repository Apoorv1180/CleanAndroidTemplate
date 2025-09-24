package com.androidcleantemplate.convention

import com.android.build.api.dsl.LibraryExtension
import com.androidcleantemplate.convention.ExtensionType
import com.androidcleantemplate.convention.configureBuildTypes
import com.androidcleantemplate.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Android Room Convention Plugin
 * 
 * This plugin configures Android library modules with Room database support
 * following our template's conventions and the 20-item checklist.
 */
class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("androidx.room")
                apply("com.google.dagger.hilt.android")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                configureKotlinAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.LIBRARY
                )
            }
        }
    }
}
