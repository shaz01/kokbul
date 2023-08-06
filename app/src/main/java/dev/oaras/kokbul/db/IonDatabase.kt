/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration

@Database(entities = [IonData::class, Answer::class], version = 4, exportSchema = true)
abstract class IonDatabase : RoomDatabase() {
    abstract fun ionDao(): IonDao
    abstract fun answerDao(): AnswerDao

    companion object {
        private const val DATABASE_NAME = "drafts.db"
        fun build(appContext: Context): IonDatabase {
            return Room.databaseBuilder(appContext, IonDatabase::class.java, DATABASE_NAME)
                .addMigrations(MIGRATION3_4)
                .createFromAsset("dbs/ions.db")
                .build()
        }

        private val MIGRATION3_4 = Migration(3, 4) { database ->
            // Create the new table with the additional fields
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS Answer (" +
                        "`knownAs` TEXT NOT NULL, " +
                        "`correct` INTEGER NOT NULL, " +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)"
            )
//            database.execSQL(
//                "CREATE TABLE new_IonData (" +
//                        "name TEXT NOT NULL, " +
//                        "knownAs TEXT NOT NULL, " +
//                        "ion TEXT NOT NULL, " +
//                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                        "timesAnswered INTEGER NOT NULL DEFAULT 0)"
//            )
//
//            // Copy data from the old table to the new table
//            database.execSQL(
//                "INSERT INTO new_IonData (name, knownAs, ion) SELECT name, knownAs, ion FROM IonData"
//            )
//
//            // Drop the old table
//            database.execSQL("DROP TABLE IonData")
//
//            // Rename the new table to IonData
//            database.execSQL("ALTER TABLE new_IonData RENAME TO IonData")
        }
    }
}

