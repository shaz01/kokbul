/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.screen.game

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oaras.kokbul.BaseViewModel
import dev.oaras.kokbul.db.IonData
import dev.oaras.kokbul.db.IonRepository
import dev.oaras.kokbul.ui.composable.Ion
import dev.oaras.kokbul.utils.updateIndexes
import dev.oaras.kokbul.utils.updateIonList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: IonRepository,
) : BaseViewModel() {
    private val mIons: MutableStateFlow<List<IonData>> = MutableStateFlow(emptyList())
    val ions = mIons.asStateFlow()
    init {
        viewModelScope.launch {
            mIons.value = repository.getAllIons() // + IonData("asd", "asad", Ion.Minus1)
            updateFields()
        }
    }

    private val mName = MutableStateFlow("" to "")
    val name = mName.asStateFlow()

    private val mAttempts = MutableStateFlow(emptyList<Ion>())
    val attempts = mAttempts.asStateFlow()

    private val mCorrectAnswer = MutableStateFlow(Ion.Minus1)
    val correctAnswer = mCorrectAnswer.asStateFlow()

    val nextEnabled = attempts.map { it.size >= 3 || it.contains(correctAnswer.value) }
    val pickEnabled = nextEnabled.map(Boolean::not)

    fun onClickBack() {
        viewModelScope.launch { finish() }
    }

    fun next() {
        val wasCorrect = mAttempts.value.size == 1
        val newList = ions.value.toMutableList()
            .updateIonList(wasCorrect)
            .updateIndexes()

        viewModelScope.launch {
            repository.addStats(mName.value.second, wasCorrect)
            repository.updateIons(newList)
        }

        mIons.value = newList
        updateFields()
    }

    private fun updateFields(){
        val ion = ions.value.firstOrNull()
        mAttempts.value = emptyList()
        if (ion == null){
            mName.value = "" to ""
            mCorrectAnswer.value = Ion.Minus1
            return
        }
        mName.value = ion.name to ion.knownAs
        mCorrectAnswer.value = ion.ion
    }

    fun pick(ion: Ion) {
        mAttempts.value += ion
        mAttempts.value.let { list ->
            if (list.size >= 3 && !list.contains(correctAnswer.value)) {
                mAttempts.value += correctAnswer.value
            }
        }
    }
}