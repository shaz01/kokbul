/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.screen.stats

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oaras.kokbul.BaseViewModel
import dev.oaras.kokbul.db.IonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StatsViewModel @Inject constructor(private val repository: IonRepository) : BaseViewModel() {
    fun backClicked() {
        viewModelScope.launch { finish() }
    }

    sealed interface State {
        object Loading : State
        class Ready(val list: List<IonRepository.IonStats>) : State
    }

    private val mState: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state = mState.asStateFlow()

    init {
        viewModelScope.launch {
            mState.value = State.Ready(repository.getAnswerList())
        }
    }


}
