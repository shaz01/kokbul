/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.navigation.routing

interface NavigableRoute<R: ScreenRoute> {

    val screenRoute: R

    val path: String

}