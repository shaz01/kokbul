/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class DataStoreOwner(context: Context, name: String) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = name)
    internal val dataStore = context.dataStore

    inner class Preference<T>(
        name: String,
        private val defValue: T,
    ) {
        private val key: Preferences.Key<T> = when (defValue!!::class) {
            Int::class -> intPreferencesKey(name)
            Boolean::class -> booleanPreferencesKey(name)
            Long::class -> longPreferencesKey(name)
            Float::class -> floatPreferencesKey(name)
            String::class -> stringPreferencesKey(name)
            else -> {
                throw IllegalArgumentException()
            }
        } as Preferences.Key<T>

        val flow = dataStore.data.mapLatest { it[key] ?: defValue }
            .stateIn(GlobalScope, SharingStarted.Eagerly, defValue)

        fun setValue(value: T){
            GlobalScope.launch {
                dataStore.edit { pref ->
                    pref[key] = value
                }
            }
        }
    }
}
