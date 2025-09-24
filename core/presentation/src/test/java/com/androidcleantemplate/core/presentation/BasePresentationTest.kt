package com.androidcleantemplate.core.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidcleantemplate.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Base test class for presentation layer tests.
 * 
 * This follows the comprehensive checklist requirement for proper testing infrastructure
 * and provides common setup for all presentation layer tests including Compose testing.
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
abstract class BasePresentationTest {
    
    /**
     * Compose test rule for UI testing.
     */
    @get:Rule
    val composeTestRule = createComposeRule()
    
    /**
     * Activity scenario rule for integration testing.
     */
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    
    /**
     * Test dispatcher for coroutines testing.
     */
    protected val testDispatcher: TestDispatcher = StandardTestDispatcher()
    
    /**
     * Setup method called before each test.
     */
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }
    
    /**
     * Teardown method called after each test.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
