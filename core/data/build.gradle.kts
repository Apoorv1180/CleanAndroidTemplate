plugins {
    alias(libs.plugins.androidcleantemplate.android.library)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
}

dependencies {
    // Core modules
    implementation(project(":core:domain"))
    implementation(project(":core:database"))
    implementation(project(":core:networking"))
    
    // Core Android
    implementation(libs.androidx.core.ktx)
    
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    
    // Serialization
    implementation(libs.kotlinx.serialization.json)
    
    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}
