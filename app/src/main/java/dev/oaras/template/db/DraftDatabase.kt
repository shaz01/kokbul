/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Draft::class], version = 2)
abstract class DraftDatabase : RoomDatabase() {
    abstract fun draftDao(): DraftDao

    companion object{
        private const val DATABASE_NAME = "drafts.db"
        fun build(appContext: Context): DraftDatabase {
            return Room.databaseBuilder(
                appContext,
                DraftDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}

