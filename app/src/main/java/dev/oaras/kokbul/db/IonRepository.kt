/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.db

import dev.oaras.kokbul.preferences.ShufflePreferences
import dev.oaras.kokbul.utils.shuffleIndexes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IonRepository @Inject constructor(
    private val dao: IonDao,
    private val answerDao: AnswerDao,
    private val shufflePreferences: ShufflePreferences
) {

    data class IonStats(
        val ionData: IonData,
        val answers: List<Boolean>
    ) {
        val timesCorrect by lazy { answers.count { it } }
        val timesIncorrect by lazy { answers.count() - timesCorrect }
    }

    suspend fun getAnswerList(): List<IonStats> {
        val ions = DATA
        return ions.map { ion ->
            IonStats(
                ionData = ion,
                answers = answerDao.getAnswers(ion.knownAs)
            )
        }
    }

    suspend fun addStats(knownAs: String, correct: Boolean){
        answerDao.insert(
            Answer(
                knownAs = knownAs,
                correct = correct
            )
        )
    }

    suspend fun getAllIons(): List<IonData> {
        val ions = dao.getAllIons()
        val shuffled = shufflePreferences.shuffled.flow.value
        if (!shuffled) {
            val new = ions.toMutableList().shuffleIndexes()
            updateIons(new)
            shufflePreferences.shuffled.setValue(true)
            return new
        }
        return ions
    }

    suspend fun updateIons(newList: MutableList<IonData>) {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
            dao.insert(*newList.toTypedArray())
        }
    }
}