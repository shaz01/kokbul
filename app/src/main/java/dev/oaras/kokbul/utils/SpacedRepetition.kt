/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.utils

import dev.oaras.kokbul.db.IonData

fun MutableList<IonData>.updateIonList(isCorrect: Boolean): MutableList<IonData> {
    if (isEmpty()) return mutableListOf()

    val ionData = removeAt(0)
    if (!isCorrect) {
        ionData.timesAnswered = 0
        add(REPETITION_FOR_WRONG.coerceAtMost(size), ionData)
    } else {
        ionData.timesAnswered = ionData.timesAnswered + 1
        if (ionData.timesAnswered < TIMES_TO_ANSWER_TO_REMOVE) {
            add(REPETITION_FOR_CORRECT.coerceAtMost(size), ionData)
        } else {
            add(size, ionData)
        }
    }
    return this
}

fun MutableList<IonData>.updateIndexes() =
    mapIndexed { index, ion ->
        ion.withIndex(index + 1L)
    }.toMutableList()

fun MutableList<IonData>.shuffleIndexes() =
    this.shuffled().mapIndexed { index, ion ->
        ion.withIndex(index.toLong())
    }.toMutableList()

private const val REPETITION_FOR_WRONG = 4
private const val REPETITION_FOR_CORRECT = 12
private const val TIMES_TO_ANSWER_TO_REMOVE = 5