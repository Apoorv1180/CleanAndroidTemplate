package com.androidcleantemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidcleantemplate.core.presentation.components.RootContent
import com.androidcleantemplate.core.presentation.navigation.BaseNavigation
import com.androidcleantemplate.core.presentation.theme.CleanAndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity for Android Clean Template.
 * 
 * This activity serves as the entry point for the application and demonstrates
 * the Root-Content pattern with Jetpack Compose and Material Design 3.
 * 
 * @see AndroidEntryPoint for Hilt dependency injection
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanAndroidTemplateTheme {
                BaseNavigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CleanAndroidTemplateTheme {
        RootContent { modifier ->
            Greeting("Android Clean Template", modifier = modifier.padding(16.dp))
        }
    }
}