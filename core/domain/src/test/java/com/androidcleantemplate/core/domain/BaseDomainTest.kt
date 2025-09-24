package com.androidcleantemplate.core.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Base test class for domain layer tests.
 * 
 * This follows the comprehensive checklist requirement for proper testing infrastructure
 * and provides common setup for all domain layer tests.
 */
@ExperimentalCoroutinesApi
abstract class BaseDomainTest {
    
    /**
     * Test dispatcher for coroutines testing.
     */
    protected val testDispatcher: TestDispatcher = StandardTestDispatcher()
    
    /**
     * Test rule for coroutines setup and teardown.
     */
    @get:Rule
    val coroutineRule = CoroutineTestRule()
    
    /**
     * Setup method called before each test.
     */
    @Before
    fun setUp() {
        // Additional setup can be added here
    }
    
    /**
     * Teardown method called after each test.
     */
    @After
    fun tearDown() {
        // Additional cleanup can be added here
    }
    
    /**
     * Test rule for managing coroutines in tests.
     */
    inner class CoroutineTestRule : TestRule {
        override fun apply(base: Statement, description: Description): Statement {
            return object : Statement() {
                override fun evaluate() {
                    Dispatchers.setMain(testDispatcher)
                    try {
                        base.evaluate()
                    } finally {
                        Dispatchers.resetMain()
                    }
                }
            }
        }
    }
}
