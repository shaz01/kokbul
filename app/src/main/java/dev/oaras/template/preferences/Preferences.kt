/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class Preferences(context: Context, name: String) {
    internal val sp: SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)
}
class IntPreference(
    private val name: String,
    private val defValue: Int,
): ReadWriteProperty<Preferences, Int> {
    override fun getValue(thisRef: Preferences, property: KProperty<*>): Int =
        thisRef.sp.getInt(name, defValue)

    override fun setValue(thisRef: Preferences, property: KProperty<*>, value: Int) {
        thisRef.sp.edit { putInt(name, value) }
    }
}
class LongPreference(
    private val name: String,
    private val defValue: Long,
): ReadWriteProperty<Preferences, Long> {
    override fun getValue(thisRef: Preferences, property: KProperty<*>): Long =
        thisRef.sp.getLong(name, defValue)

    override fun setValue(thisRef: Preferences, property: KProperty<*>, value: Long) {
        thisRef.sp.edit { putLong(name, value) }
    }
}
class BoolPreference(
    private val name: String,
    private val defValue: Boolean,
): ReadWriteProperty<Preferences, Boolean> {
    override fun getValue(thisRef: Preferences, property: KProperty<*>): Boolean =
        thisRef.sp.getBoolean(name, defValue)

    override fun setValue(thisRef: Preferences, property: KProperty<*>, value: Boolean) {
        thisRef.sp.edit { putBoolean(name, value) }
    }
}
class StringPreference(
    private val name: String,
    private val defValue: Boolean,
): ReadWriteProperty<Preferences, Boolean> {
    override fun getValue(thisRef: Preferences, property: KProperty<*>): Boolean =
        thisRef.sp.getBoolean(name, defValue)

    override fun setValue(thisRef: Preferences, property: KProperty<*>, value: Boolean) {
        thisRef.sp.edit { putBoolean(name, value) }
    }
}



