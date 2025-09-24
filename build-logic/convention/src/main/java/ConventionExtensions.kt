package com.androidcleantemplate.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Extension type enum for different Android module types
 */
enum class ExtensionType {
    APPLICATION,
    LIBRARY
}

/**
 * Configures Kotlin Android settings
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    dependencies {
        add("implementation", "androidx.core:core-ktx:1.17.0")
    }
}

/**
 * Configures Kotlin Compose settings
 */
internal fun Project.configureKotlinCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.8"
        }
    }

    dependencies {
        add("implementation", platform("androidx.compose:compose-bom:2024.09.00"))
        add("implementation", "androidx.compose.ui:ui")
        add("implementation", "androidx.compose.ui:ui-graphics")
        add("implementation", "androidx.compose.ui:ui-tooling-preview")
        add("implementation", "androidx.compose.material3:material3")
        add("debugImplementation", "androidx.compose.ui:ui-tooling")
        add("debugImplementation", "androidx.compose.ui:ui-test-manifest")
    }
}

/**
 * Configures build types for different module types
 */
internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *>,
    extensionType: ExtensionType,
) {
    commonExtension.apply {
        buildTypes {
            debug {
                isMinifyEnabled = false
                if (extensionType == ExtensionType.APPLICATION) {
                    isDebuggable = true
                }
            }
            release {
                isMinifyEnabled = extensionType == ExtensionType.APPLICATION
                if (extensionType == ExtensionType.APPLICATION) {
                    isDebuggable = false
                }
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}
