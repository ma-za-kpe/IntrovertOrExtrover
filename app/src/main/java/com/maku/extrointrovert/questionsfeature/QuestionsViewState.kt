package com.maku.extrointrovert.questionsfeature

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.Trait

// TODO: add animal view state
data class QuestionsViewState(
    val traits: List<Trait> = emptyList(),
    val questions: List<Question> = emptyList(),
    val answers: List<Answer> = emptyList(),
    val index: Int = 0,
    val enableButton: Boolean = false,
    val selectedValue: String = ""
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
            selectedValue =selectedValue
        )
    }
}