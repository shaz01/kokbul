/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg answer: Answer)

    @Query("SELECT correct FROM answer WHERE knownAs= :knownAs")
    suspend fun getAnswers(knownAs: String): List<Boolean>

    @Query("SELECT * FROM answer")
    suspend fun getAllAnswers(): List<Answer>

    @Query("SELECT count(id) FROM answer")
    suspend fun getCount(): Int

    @Query("DELETE FROM answer")
    suspend fun deleteAll()
}