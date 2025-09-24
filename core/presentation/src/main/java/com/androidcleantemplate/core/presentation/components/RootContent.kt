package com.androidcleantemplate.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidcleantemplate.core.presentation.theme.CleanAndroidTemplateTheme

/**
 * Root component following the Root-Content pattern from the comprehensive checklist.
 * 
 * This component provides the basic structure for all screens in the application,
 * including Scaffold, SnackbarHost, and proper content slots for customization.
 * 
 * @param modifier Modifier to be applied to the root component
 * @param snackbarHostState State for managing snackbars
 * @param topBar Optional top bar composable
 * @param bottomBar Optional bottom bar composable
 * @param floatingActionButton Optional floating action button
 * @param content The main content of the screen
 */
@Composable
fun RootContent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content(Modifier.fillMaxSize())
        }
    }
}

/**
 * Simple root content without scaffold for cases where custom layout is needed.
 * 
 * @param modifier Modifier to be applied to the root component
 * @param content The main content of the screen
 */
@Composable
fun SimpleRootContent(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        content(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun RootContentPreview() {
    CleanAndroidTemplateTheme {
        RootContent(
            content = { modifier ->
                Box(modifier = modifier.padding(16.dp)) {
                    // Preview content
                }
            }
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RootContentDarkPreview() {
    CleanAndroidTemplateTheme {
        RootContent(
            content = { modifier ->
                Box(modifier = modifier.padding(16.dp)) {
                    // Preview content
                }
            }
        )
    }
}
