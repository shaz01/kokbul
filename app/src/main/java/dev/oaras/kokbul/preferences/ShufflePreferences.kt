/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShufflePreferences @Inject constructor(@ApplicationContext context: Context):
    DataStoreOwner(context = context, name = TAG) {

    /**
     * Shuffled the database before or not.
     */
    var shuffled = Preference("shuffled", defValue = false)

    companion object{
        private const val TAG = "shuffler"
    }
}