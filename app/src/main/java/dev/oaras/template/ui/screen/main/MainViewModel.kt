/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.screen.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oaras.template.BaseViewModel
import dev.oaras.template.db.Draft
import dev.oaras.template.db.DraftRepository
import dev.oaras.template.navigation.routing.generatePath
import dev.oaras.template.ui.Routes
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: DraftRepository
) : BaseViewModel() {


    val drafts = repo.getDrafts()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = PagingData.empty(),
        )

    fun onClickAdd() = viewModelScope.launch {
        navigateTo(Routes.Add.generatePath())
    }

    fun delete(draft: Draft) = viewModelScope.launch { repo.delete(draft) }
    fun edit(draft: Draft) {
        viewModelScope.launch {
            navigateTo(
                Routes.Edit.generatePath(
                    "id" to draft.id
                )
            )
        }
    }

    fun view(draft: Draft) {
        viewModelScope.launch {
            navigateTo(
                Routes.View.generatePath(
                    "id" to draft.id
                )
            )
        }
    }
}