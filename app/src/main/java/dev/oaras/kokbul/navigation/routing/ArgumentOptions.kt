/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.navigation.routing

import androidx.navigation.NavType

/**
 * Arguments Options specify for the argument keys used on the Screen Routes
 * @param type argument type
 * @param optional true if argument is optional
 */
class ArgumentOptions(
    var type: NavType<*> = NavType.StringType,
    var optional: Boolean = false
)