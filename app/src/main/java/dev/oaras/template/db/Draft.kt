/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.oaras.template.db.converters.UriTypeConverter

@Entity
@TypeConverters(UriTypeConverter::class)
data class Draft(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val time: Long,
    val modified: Long,
    val draft: String,
) {
    override fun toString(): String {
        return "Draft(id=$id, time=$time, modified=$modified, draft='$draft')"
    }
}
