plugins {
    alias(libs.plugins.androidcleantemplate.android.room)
}

dependencies {
    // Core modules
    implementation(project(":core:domain"))
    
    // Core Android
    implementation(libs.androidx.core.ktx)
    
    // Room Database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    
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
