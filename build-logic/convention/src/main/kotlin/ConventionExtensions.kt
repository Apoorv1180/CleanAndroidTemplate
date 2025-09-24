package com.androidcleantemplate.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
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
 * Configures Kotlin Android settings for Application modules
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: ApplicationExtension,
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
            targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
        }
    }

    dependencies {
        add("implementation", "androidx.core:core-ktx:1.17.0")
    }
}

/**
 * Configures Kotlin Android settings for Library modules
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: LibraryExtension,
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
            targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
        }
    }

    dependencies {
        add("implementation", "androidx.core:core-ktx:1.17.0")
    }
}

/**
 * Configures Kotlin Compose settings for Application modules
 */
internal fun Project.configureKotlinCompose(
    commonExtension: ApplicationExtension,
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
 * Configures Kotlin Compose settings for Library modules
 */
internal fun Project.configureKotlinCompose(
    commonExtension: LibraryExtension,
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
 * Configures build types for Application modules
 */
internal fun Project.configureBuildTypes(
    commonExtension: ApplicationExtension,
    extensionType: ExtensionType,
) {
    commonExtension.apply {
        buildTypes {
            debug {
                isMinifyEnabled = false
                isDebuggable = true
            }
            release {
                isMinifyEnabled = true
                isDebuggable = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}

/**
 * Configures build types for Library modules
 */
internal fun Project.configureBuildTypes(
    commonExtension: LibraryExtension,
    extensionType: ExtensionType,
) {
    commonExtension.apply {
        buildTypes {
            debug {
                isMinifyEnabled = false
            }
            release {
                isMinifyEnabled = false
            }
        }
    }
}
