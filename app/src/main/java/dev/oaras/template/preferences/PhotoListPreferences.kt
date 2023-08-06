/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.photodeleter.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.oaras.template.preferences.BoolPreference
import dev.oaras.template.preferences.LongPreference
import dev.oaras.template.preferences.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoListPreferences @Inject constructor(@ApplicationContext context: Context):
    Preferences(context = context, name = TAG) {

    var lastBuild by LongPreference("lastBuilt", 0)
    var autoBuild by BoolPreference("autoBuild", true)
//    var autoBuildInterval by

    fun setLastBuilt(){
        lastBuild = System.currentTimeMillis()
    }
    companion object{
        private const val TAG = "PL"
    }
}