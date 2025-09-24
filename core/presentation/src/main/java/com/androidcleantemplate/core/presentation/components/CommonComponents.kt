package com.androidcleantemplate.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidcleantemplate.core.presentation.theme.CleanAndroidTemplateTheme

/**
 * Loading component following the comprehensive checklist requirements.
 * 
 * This component provides a consistent loading state across the application
 * with proper theming and accessibility support.
 * 
 * @param modifier Modifier to be applied to the component
 * @param message Optional loading message to display
 * @param size Size of the progress indicator
 */
@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier,
    message: String? = null,
    size: Int = 40
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size.dp),
            color = MaterialTheme.colorScheme.primary
        )
        
        if (message != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Error component following the comprehensive checklist requirements.
 * 
 * This component provides a consistent error state across the application
 * with proper theming and retry functionality.
 * 
 * @param modifier Modifier to be applied to the component
 * @param message Error message to display
 * @param onRetry Callback for retry action
 * @param retryText Text for the retry button
 */
@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: (() -> Unit)? = null,
    retryText: String = "Retry"
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
        
        if (onRetry != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = retryText)
            }
        }
    }
}

/**
 * Empty state component following the comprehensive checklist requirements.
 * 
 * This component provides a consistent empty state across the application
 * with proper theming and optional action buttons.
 * 
 * @param modifier Modifier to be applied to the component
 * @param title Title for the empty state
 * @param message Description for the empty state
 * @param primaryAction Primary action button configuration
 * @param secondaryAction Secondary action button configuration
 */
@Composable
fun EmptyStateComponent(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    primaryAction: ActionButton? = null,
    secondaryAction: ActionButton? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        
        if (primaryAction != null || secondaryAction != null) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                secondaryAction?.let { action ->
                    OutlinedButton(
                        onClick = action.onClick,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = action.text)
                    }
                }
                
                primaryAction?.let { action ->
                    Button(
                        onClick = action.onClick,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = action.text)
                    }
                }
            }
        }
    }
}

/**
 * Data class for action button configuration.
 */
data class ActionButton(
    val text: String,
    val onClick: () -> Unit
)

@Preview(showBackground = true)
@Composable
fun LoadingComponentPreview() {
    CleanAndroidTemplateTheme {
        LoadingComponent(
            message = "Loading..."
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorComponentPreview() {
    CleanAndroidTemplateTheme {
        ErrorComponent(
            message = "Something went wrong. Please try again.",
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyStateComponentPreview() {
    CleanAndroidTemplateTheme {
        EmptyStateComponent(
            title = "No Data",
            message = "There's no data to display at the moment.",
            primaryAction = ActionButton("Refresh") {},
            secondaryAction = ActionButton("Settings") {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ComponentsDarkPreview() {
    CleanAndroidTemplateTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LoadingComponent(message = "Loading...")
            ErrorComponent(message = "Error occurred")
            EmptyStateComponent(
                title = "No Data",
                message = "Empty state message"
            )
        }
    }
}
