package com.androidcleantemplate.core.presentation.navigation

/**
 * Navigation routes for the Android Clean Template.
 * 
 * This follows the comprehensive checklist requirement for navigation structure
 * and provides a centralized way to manage all navigation routes in the application.
 */
object NavigationRoutes {
    // Main routes
    const val HOME = "home"
    const val SPLASH = "splash"
    
    // Feature routes (optional modules)
    const val AUTH_LOGIN = "auth/login"
    const val AUTH_REGISTER = "auth/register"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
    
    // Nested routes
    const val HOME_DETAIL = "home/detail/{id}"
    const val PROFILE_EDIT = "profile/edit"
    
    /**
     * Helper function to create detail route with ID.
     */
    fun createDetailRoute(id: String): String = "home/detail/$id"
    
    /**
     * Helper function to extract ID from detail route.
     */
    fun extractIdFromDetailRoute(route: String): String? {
        return route.substringAfterLast("/")
    }
}

/**
 * Navigation arguments for type-safe navigation.
 * 
 * This follows the comprehensive checklist requirement for proper navigation
 * with type safety and argument validation.
 */
object NavigationArgs {
    const val ID = "id"
    const val USER_ID = "userId"
    const val TITLE = "title"
    const val MESSAGE = "message"
}
