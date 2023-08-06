/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.oaras.kokbul.BaseViewModel
import dev.oaras.kokbul.navigation.routing.ScreenRoute

/**
 * Navigation Component used for the routing
 * @param startRoute starting route
 * @param builder NavGraph
 */
@Composable
internal fun NavigationComponent(
    startRoute: ScreenRoute,
    navigationController: NavigationController,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navigationController.getNavController(),
        startDestination = startRoute.routeDefinition.getRoutePath(),
        builder = builder
    )
}

/**
 * Provides the ViewModel and attach che navigationController
 * @param parent parent Screen Route
 */
@Composable
private inline fun <reified VM : BaseViewModel> provideHiltViewModel(
    parent: ScreenRoute? = null,
    navigationController: NavigationController
): VM {
    return parent?.let {
        hiltViewModel(
            navigationController.getNavController().getBackStackEntry(it)
        )
    } ?: hiltViewModel()
}

/**
 * Wrapper used to add composable starting from a ScreenRouter
 * @param route screen's route
 * @param parentRoute screen's parent route
 * @param content content associated to the route
 */
internal inline fun <reified VM : BaseViewModel> NavGraphBuilder.composable(
    route: ScreenRoute,
    parentRoute: ScreenRoute? = null,
    navigationController: NavigationController,
    crossinline content: @Composable (NavBackStackEntry, VM) -> Unit
) {
    this.composable(
        route = route.routeDefinition.getRoutePath(),
        arguments = route.routeDefinition.getNavArguments(),
        content = { navBackStackEntry ->
            val viewModel: VM = provideHiltViewModel(
                parent = parentRoute,
                navigationController = navigationController
            )
            viewModel.setNavigationController(navigationController)
            content.invoke(
                navBackStackEntry,
                viewModel
            )
        }
    )
}

/**
 * Wrapper used to add composable starting from a ScreenRouter
 * @param route screen's route
 * @param content content associated to the route
 */
internal inline fun NavGraphBuilder.composable(
    route: ScreenRoute,
    crossinline content: @Composable (NavBackStackEntry) -> Unit
) {
    this.composable(
        route = route.routeDefinition.getRoutePath(),
        arguments = route.routeDefinition.getNavArguments(),
        content = { navBackStackEntry ->
            content.invoke(navBackStackEntry)
        }
    )
}

/**
 * Construct a nested [NavGraph]
 *
 * @param startDestination the starting destination's route for this NavGraph
 * @param route the destination's unique route
 * @param builder the builder used to construct the graph
 *
 * @return the newly constructed nested NavGraph
 */
internal inline fun NavGraphBuilder.navigation(
    startDestination: ScreenRoute,
    route: ScreenRoute,
    builder: NavGraphBuilder.() -> Unit
): Unit = navigation(
    startDestination = startDestination.routeDefinition.getRoutePath(),
    route = route.routeDefinition.getRoutePath(),
    builder = builder
)

/**
 * Gets the topmost [NavBackStackEntry] for a route.
 *
 * This is always safe to use with [the current destination][route] or
 * [its parent][NavDestination.parent] or grandparent navigation graphs as these
 * destinations are guaranteed to be on the back stack.
 *
 * @param route route of a destination that exists on the back stack
 * @throws IllegalArgumentException if the destination is not on the back stack
 */
internal fun NavController.getBackStackEntry(route: ScreenRoute): NavBackStackEntry {
    return this.getBackStackEntry(route.routeDefinition.getRoutePath())
}