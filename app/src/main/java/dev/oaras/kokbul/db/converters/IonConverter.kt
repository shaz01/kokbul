/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.db.converters

import androidx.room.TypeConverter
import dev.oaras.kokbul.ui.composable.Ion

class IonConverter {
    @TypeConverter
    fun fromString(value: String?): Ion? = value?.let { Ion.valueOf(it) }

    @TypeConverter
    fun toString(ion: Ion?): String? = ion?.name
}