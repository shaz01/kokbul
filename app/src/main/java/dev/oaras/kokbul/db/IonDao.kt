/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IonDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg ionData: IonData)

    @Query("SELECT * FROM iondata WHERE knownAs= :knownAs")
    suspend fun getIon(knownAs: String): IonData

    @Query("SELECT * FROM iondata")
    fun getPagingSource(): PagingSource<Int, IonData>

    @Query("SELECT * FROM iondata")
    suspend fun getAllIons(): List<IonData>

//    @Query("SELECT * FROM iondata ORDER BY name")
//    suspend fun getAllIonsSorted(): List<IonData>

    @Query("SELECT count(name) FROM iondata")
    suspend fun getCount(): Int

    @Query("DELETE FROM iondata")
    suspend fun deleteAll()
}
