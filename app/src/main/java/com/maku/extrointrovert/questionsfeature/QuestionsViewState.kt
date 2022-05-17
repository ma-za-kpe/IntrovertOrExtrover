package com.maku.extrointrovert.questionsfeature

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question

// TODO: add animal view state
data class QuestionsViewState(
    val loading: Boolean = true,
    val questions: List<Question> = emptyList(),
    val answers: List<Answer> = emptyList(),

    )