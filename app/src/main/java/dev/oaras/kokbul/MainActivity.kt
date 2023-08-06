/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.oaras.kokbul.navigation.NavigationComponent
import dev.oaras.kokbul.navigation.NavigationControllerImpl
import dev.oaras.kokbul.navigation.composable
import dev.oaras.kokbul.ui.Routes
import dev.oaras.kokbul.ui.screen.game.GameScreen
import dev.oaras.kokbul.ui.screen.game.GameViewModel
import dev.oaras.kokbul.ui.screen.stats.StatsScreen
import dev.oaras.kokbul.ui.screen.stats.StatsViewModel
import dev.oaras.kokbul.ui.screen.title.TitleScreen
import dev.oaras.kokbul.ui.screen.title.TitleViewModel
import dev.oaras.kokbul.ui.theme.TemplateTheme
import dev.oaras.kokbul.utils.EdgeToEdge

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
                        startRoute = Routes.Title,
                        navigationController = controller
                    ) {
                        composable<TitleViewModel>(
                            route = Routes.Title,
                            navigationController = controller,
                        ) { _, vm ->
                            TitleScreen(vm)
                        }
                        composable<GameViewModel>(
                            route = Routes.Game,
                            navigationController = controller
                        ) { _, vm ->
                            GameScreen(viewModel = vm)
                        }
                        composable<StatsViewModel>(
                            route = Routes.Stats,
                            navigationController = controller
                        ) { _, vm ->
                            StatsScreen(vm = vm)
                        }
                    }
                }
            }
        }
    }
}