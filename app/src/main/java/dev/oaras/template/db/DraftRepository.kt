/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.db

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DraftRepository @Inject constructor(
    private val dao: DraftDao,
) {
    companion object {
        const val PAGE_SIZE = 100
    }

    suspend fun insert(draft: String) {
        dao.insert(
            Draft(
                id = 0L,
                time = System.currentTimeMillis(),
                modified = System.currentTimeMillis(),
                draft = draft
            )
        )
    }

    suspend fun getDraft(id: Long): Draft = dao.getDraft(id)
    fun getDraftFlow(id: Long): Flow<Draft> = dao.getDraftFlow(id)

    suspend fun update(draft: Draft) {
        dao.update(draft.copy(modified = System.currentTimeMillis()))
    }

    suspend fun delete(draft: Draft) = dao.delete(draft)

    fun getDrafts(pageSize: Int = PAGE_SIZE) = Pager(
        config = PagingConfig(pageSize = pageSize),
        pagingSourceFactory = { dao.getPagingSource() }
    ).flow

    fun update(id: Long, draft: String) {

    }

}