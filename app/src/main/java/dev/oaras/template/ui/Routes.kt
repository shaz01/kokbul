/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui

import androidx.navigation.NavType
import dev.oaras.template.navigation.routing.ScreenRoute

object Routes {
    object Main : ScreenRoute(routeDefinition = Definition("main"))
    object Add : ScreenRoute(routeDefinition = Definition("add"))
    object Edit : ScreenRoute(
        routeDefinition = Definition(
            routePath = "edit",
            argumentKeys = listOf(
                "id" to { type = NavType.LongType; optional = false }
            )
        )
    )
    object View: ScreenRoute(
        routeDefinition = Definition(
            routePath = "view",
            argumentKeys = listOf(
                "id" to { type = NavType.LongType; optional = false }
            )
        )
    )

    object Dashboard : ScreenRoute(
        routeDefinition = Definition("dashboard", argumentKeys = listOf(
            "username" to { type = NavType.StringType; optional = false }
        ))
    )
}