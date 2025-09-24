

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * JVM Library Convention Plugin
 * 
 * This plugin configures JVM library modules for pure Kotlin libraries
 * following our template's conventions and the 20-item checklist.
 * Used for domain modules that don't need Android dependencies.
 */
class JvmLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.jvm")
            }
            extensions.configure<KotlinJvmProjectExtension> {
                jvmToolchain(11)
            }
        }
    }
}
