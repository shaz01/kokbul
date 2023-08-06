/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.navigation

import androidx.navigation.NavHostController
import dev.oaras.kokbul.navigation.routing.NavigableRoute
import dev.oaras.kokbul.navigation.routing.ScreenRoute

interface NavigationController {

    /**
     * Return the [NavHostController] bind to the NavigationController
     */
    fun getNavController(): NavHostController

    /**
     * Navigate to the [destinationRoute]
     * @param destinationRoute destination
     */
    suspend fun <T : ScreenRoute> navigateTo(
        destinationRoute: NavigableRoute<T>
    )

    /**
     * Navigate back to the [destinationRoute] with the previous route [parentRoute]
     * @param destinationRoute destination route
     * @param parentRoute previous routes
     */
    suspend fun <R : ScreenRoute, PR : ScreenRoute> navigateBackTo(
        destinationRoute: NavigableRoute<R>,
        parentRoute: NavigableRoute<PR>
    )

    suspend fun navigateUp()
}