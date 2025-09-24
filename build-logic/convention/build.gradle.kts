@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
}

group = "com.androidcleantemplate.buildlogic"

dependencies {
    compileOnly("com.android.tools.build:gradle:8.3.2")
    compileOnly("com.android.tools:common:31.8.0")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
    compileOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.21-1.0.28")
    compileOnly("androidx.room:room-gradle-plugin:2.6.1")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "androidcleantemplate.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "androidcleantemplate.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "androidcleantemplate.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "androidcleantemplate.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "androidcleantemplate.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidRoom") {
            id = "androidcleantemplate.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "androidcleantemplate.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
