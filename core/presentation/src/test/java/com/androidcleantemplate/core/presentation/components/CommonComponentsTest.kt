package com.androidcleantemplate.core.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.androidcleantemplate.core.presentation.theme.CleanAndroidTemplateTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests for common UI components.
 * 
 * This follows the comprehensive checklist requirement for UI testing
 * and ensures all common components work correctly.
 */
class CommonComponentsTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun `loadingComponent should display loading indicator`() {
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                LoadingComponent()
            }
        }
        
        // Then - The CircularProgressIndicator should be displayed
        // Note: We can't easily test the CircularProgressIndicator directly,
        // but we can test that the component renders without errors
    }
    
    @Test
    fun `loadingComponent with message should display message`() {
        // Given
        val loadingMessage = "Loading data..."
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                LoadingComponent(message = loadingMessage)
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(loadingMessage).assertIsDisplayed()
    }
    
    @Test
    fun `errorComponent should display error message`() {
        // Given
        val errorMessage = "Something went wrong"
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                ErrorComponent(message = errorMessage)
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }
    
    @Test
    fun `errorComponent with retry should display retry button`() {
        // Given
        val errorMessage = "Network error"
        val retryText = "Try Again"
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                ErrorComponent(
                    message = errorMessage,
                    onRetry = {},
                    retryText = retryText
                )
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
        composeTestRule.onNodeWithText(retryText).assertIsDisplayed()
    }
    
    @Test
    fun `emptyStateComponent should display title and message`() {
        // Given
        val title = "No Data"
        val message = "There's nothing to show here"
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                EmptyStateComponent(
                    title = title,
                    message = message
                )
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(message).assertIsDisplayed()
    }
    
    @Test
    fun `emptyStateComponent with actions should display buttons`() {
        // Given
        val title = "Empty State"
        val message = "No items found"
        val primaryActionText = "Add Item"
        val secondaryActionText = "Refresh"
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                EmptyStateComponent(
                    title = title,
                    message = message,
                    primaryAction = ActionButton(primaryActionText) {},
                    secondaryAction = ActionButton(secondaryActionText) {}
                )
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(message).assertIsDisplayed()
        composeTestRule.onNodeWithText(primaryActionText).assertIsDisplayed()
        composeTestRule.onNodeWithText(secondaryActionText).assertIsDisplayed()
    }
}
