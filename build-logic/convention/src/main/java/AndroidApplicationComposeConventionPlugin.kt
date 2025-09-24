package com.androidcleantemplate.convention

import com.android.build.api.dsl.ApplicationExtension
import com.androidcleantemplate.convention.ExtensionType
import com.androidcleantemplate.convention.configureBuildTypes
import com.androidcleantemplate.convention.configureKotlinAndroid
import com.androidcleantemplate.convention.configureKotlinCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Android Application Compose Convention Plugin
 * 
 * This plugin configures Android application modules with Compose support
 * following our template's conventions and the 20-item checklist.
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = "com.androidcleantemplate"
                    targetSdk = 35
                    versionCode = 1
                    versionName = "1.0"
                }

                configureKotlinAndroid(this)
                configureKotlinCompose(this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }
        }
    }
}
