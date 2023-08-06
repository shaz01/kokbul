/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.oaras.template.navigation.NavigationComponent
import dev.oaras.template.navigation.NavigationControllerImpl
import dev.oaras.template.navigation.composable
import dev.oaras.template.ui.Routes
import dev.oaras.template.ui.screen.add.AddScreen
import dev.oaras.template.ui.screen.add.AddViewModel
import dev.oaras.template.ui.screen.edit.EditScreen
import dev.oaras.template.ui.screen.edit.EditViewModel
import dev.oaras.template.ui.screen.main.MainScreen
import dev.oaras.template.ui.screen.main.MainViewModel
import dev.oaras.template.ui.screen.view.ViewScreen
import dev.oaras.template.ui.screen.view.ViewViewModel
import dev.oaras.template.ui.theme.TemplateTheme
import dev.oaras.template.utils.EdgeToEdge

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EdgeToEdge.setTranslucent(this)
        setContent {
            val navHostState = rememberNavController()
            val controller = NavigationControllerImpl(navHostState)
            TemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComponent(
                        startRoute = Routes.Main,
                        navigationController = controller
                    ) {
                        composable<MainViewModel>(
                            route = Routes.Main,
                            navigationController = controller,
                        ) { _, vm ->
                            MainScreen(vm)
                        }

                        composable<AddViewModel>(
                            route = Routes.Add,
                            navigationController = controller
                        ) { _, vm ->
                            AddScreen(vm)
                        }
                        composable<EditViewModel>(
                            route = Routes.Edit,
                            navigationController = controller
                        ) { _, vm ->
                            EditScreen(vm)
                        }
                        composable<ViewViewModel>(
                            route = Routes.View,
                            navigationController = controller
                        ) { _, vm ->
                            ViewScreen(vm)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!".repeat(2000),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemplateTheme {
        Greeting("Android")
    }
}