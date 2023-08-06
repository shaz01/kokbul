/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toTimeString(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.format(date)
}