package com.androidcleantemplate.convention

import com.android.build.api.dsl.ApplicationExtension
import com.androidcleantemplate.convention.ExtensionType
import com.androidcleantemplate.convention.configureBuildTypes
import com.androidcleantemplate.convention.configureKotlinAndroid
import com.androidcleantemplate.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Android Application Convention Plugin
 * 
 * This plugin configures Android application modules with standard settings
 * following our template's conventions and the 20-item checklist.
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                configureKotlinAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }
        }
    }
}
