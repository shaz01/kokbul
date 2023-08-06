/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.screen.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oaras.template.BaseViewModel
import dev.oaras.template.db.DraftRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: DraftRepository
) : BaseViewModel() {
    private val mDraft = MutableStateFlow("")
    val draft: StateFlow<String> get() = mDraft
    val buttonEnabled = draft.map { it.isNotBlank() }

    fun onChangeDraft(draft: String) {
        mDraft.value = draft
    }

    fun add(){
        viewModelScope.launch {
            repository.insert(mDraft.value)
            finish()
        }
    }


    //TODO: Add "Are you sure you want to exit? Unsaved changes will be lost."
    fun onClickBack() {
        viewModelScope.launch { finish() }
    }
}