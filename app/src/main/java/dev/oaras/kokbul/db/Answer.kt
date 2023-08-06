/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Answer(
    val knownAs: String,
    val correct: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
)
//@ForeignKey(entity = IonData::class)
