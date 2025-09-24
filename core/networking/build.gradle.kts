plugins {
    alias(libs.plugins.androidcleantemplate.android.library)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
}

dependencies {
    // Core modules
    implementation(project(":core:domain"))
    
    // Core Android
    implementation(libs.androidx.core.ktx)
    
    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    
    // Logging
    implementation(libs.timber)
    
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
