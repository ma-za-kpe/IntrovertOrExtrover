package com.maku.extrointrovert.questionsfeature

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.Trait

data class QuestionsViewState(
    val questions: List<Question> = emptyList(),
    val answers: List<Answer> = emptyList(),
    val index: Int = 0,
    val enableButton: Boolean = false,
    val selectedValue: String = "",
    val survey: String = "survey"
) {
    fun updateIndex(index: Int): QuestionsViewState {
        return copy(
            index = index
        )
    }

    fun updateButtonEnabled(enabled: Boolean): QuestionsViewState {
        return copy(
            enableButton = enabled
        )
    }

    fun updateSelectedValue(selectedValue: String): QuestionsViewState {
        return copy(
            selectedValue = selectedValue
        )
    }

    fun updateScreenState(state: String): QuestionsViewState {
        return copy(survey = state)
    }
}