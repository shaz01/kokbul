/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.screen.view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oaras.template.BaseViewModel
import dev.oaras.template.db.Draft
import dev.oaras.template.db.DraftRepository
import dev.oaras.template.navigation.routing.generatePath
import dev.oaras.template.ui.Routes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewViewModel @Inject constructor(
    repository: DraftRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val id = savedStateHandle.get<Long>("id") ?: throw IllegalStateException()
    private var draft: StateFlow<Draft?> = repository.getDraftFlow(id).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )
    val content: Flow<String?> = draft.map { it?.draft }
    val loading = content.map { it == null }

    fun onClickEdit() {
        viewModelScope.launch {
            draft.value.let { draft ->
                if (draft == null) return@let
                navigateTo(
                    Routes.Edit.generatePath(
                        "id" to draft.id
                    )
                )
            }
        }
    }


    //TODO: Add "Are you sure you want to exit? Unsaved changes will be lost."
    fun onClickBack() {
        viewModelScope.launch { finish() }
    }
}