/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui

import dev.oaras.kokbul.navigation.routing.ScreenRoute

object Routes {
    object Title: ScreenRoute(routeDefinition = Definition("title"))
    object Game: ScreenRoute(routeDefinition = Definition("game"))
    object Stats: ScreenRoute(routeDefinition = Definition("stats"))

}