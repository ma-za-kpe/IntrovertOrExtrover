package com.maku.extrointrovert.questionsfeature

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question

// TODO: add animal view state
data class QuestionsViewState(
    val questions: List<Question> = emptyList(),
    val answers: List<Answer> = emptyList(),
    val index: Int = 0
) {
    fun updateIndex(index: Int): QuestionsViewState {
        return copy(
            index = index
        )
    }
}