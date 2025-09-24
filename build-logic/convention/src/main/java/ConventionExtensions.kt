package com.androidcleantemplate.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Extension type enum for different Android module types
 */
enum class ExtensionType {
    APPLICATION,
    LIBRARY
}

/**
 * Gets the version catalog from the project
 */
internal val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Configures Kotlin Android settings
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("projectCompileSdkVersion").get().toString().toInt()

        defaultConfig {
            minSdk = libs.findVersion("projectMinSdkVersion").get().toString().toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    dependencies {
        add("implementation", libs.findLibrary("androidx-core-ktx").get())
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
            kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler").get().toString()
        }
    }

    dependencies {
        val composeBom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(composeBom))
        add("implementation", libs.findLibrary("androidx-compose-ui").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-graphics").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        add("implementation", libs.findLibrary("androidx-compose-material3").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
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
