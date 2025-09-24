pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "CleanAndroidTemplate"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Core modules (always present)
include(":core:domain")
include(":core:data")
include(":core:presentation")
include(":core:database")
include(":core:networking")

// Main app module
include(":app")
