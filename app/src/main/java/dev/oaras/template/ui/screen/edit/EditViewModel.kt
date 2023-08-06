/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.screen.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oaras.template.BaseViewModel
import dev.oaras.template.db.Draft
import dev.oaras.template.db.DraftRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val repository: DraftRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val id = savedStateHandle.get<Long>("id") ?: throw IllegalStateException()
    lateinit var draft: Draft
    private val mContent: MutableStateFlow<String?> = MutableStateFlow(null)
    val content: StateFlow<String?> get() = mContent
    val loading = content.map { it == null }
    val buttonEnabled = content.map { !it.isNullOrBlank() }
    init {
        viewModelScope.launch {
            draft = repository.getDraft(id = id)
            mContent.value = draft.draft
        }
    }

    fun onChangeDraft(draft: String) {
        mContent.value = draft
    }

    fun add() {
        viewModelScope.launch {
            mContent.value.let { content ->
                if (content == null) return@let
                repository.update(draft.copy(draft = content))
                finish()
            }
        }
    }


    //TODO: Add "Are you sure you want to exit? Unsaved changes will be lost."
    fun onClickBack() {
        viewModelScope.launch { finish() }
    }
}