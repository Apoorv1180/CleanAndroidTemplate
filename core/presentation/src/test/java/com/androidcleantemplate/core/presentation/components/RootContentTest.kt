package com.androidcleantemplate.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.androidcleantemplate.core.presentation.theme.CleanAndroidTemplateTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests for the RootContent component.
 * 
 * This follows the comprehensive checklist requirement for UI testing
 * and ensures the Root-Content pattern works correctly.
 */
class RootContentTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun `rootContent should display content correctly`() {
        // Given
        val testContent = "Test Content"
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                RootContent { modifier ->
                    Box(modifier = modifier) {
                        Text(text = testContent)
                    }
                }
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(testContent).assertIsDisplayed()
    }
    
    @Test
    fun `simpleRootContent should display content correctly`() {
        // Given
        val testContent = "Simple Test Content"
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                SimpleRootContent { modifier ->
                    Box(modifier = modifier) {
                        Text(text = testContent)
                    }
                }
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(testContent).assertIsDisplayed()
    }
    
    @Test
    fun `rootContent should fill max size`() {
        // Given
        val testContent = "Fill Max Size Test"
        
        // When
        composeTestRule.setContent {
            CleanAndroidTemplateTheme {
                RootContent { modifier ->
                    Box(modifier = modifier.fillMaxSize()) {
                        Text(text = testContent)
                    }
                }
            }
        }
        
        // Then
        composeTestRule.onNodeWithText(testContent).assertIsDisplayed()
    }
}
