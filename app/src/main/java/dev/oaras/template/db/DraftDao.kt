/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.db

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg draft: Draft)

    @Query("SELECT * FROM draft WHERE id = :id")
    suspend fun getDraft(id: Long): Draft

    @Query("SELECT * FROM draft WHERE id = :id")
    fun getDraftFlow(id: Long): Flow<Draft>

    @Update
    suspend fun update(vararg draft: Draft)


    @Delete
    suspend fun delete(draft: Draft)

//    @Query("SELECT * FROM draft LIMIT :limit OFFSET :offset")
//    suspend fun getPagedList(limit: Int, offset: Int): List<Draft>


    @Query("SELECT * FROM draft")
    fun getPagingSource(): PagingSource<Int, Draft>

//    @Query("SELECT * FROM photo WHERE deleted = false AND CONTAINS(title, :filter) ORDER BY uri")
//    fun getPagingSource(filter: String): PagingSource<Int, Photo>

    @Query("SELECT count(id) FROM draft")
    suspend fun getCount(): Int

    @Query("DELETE FROM draft")
    suspend fun deleteAll()
}
