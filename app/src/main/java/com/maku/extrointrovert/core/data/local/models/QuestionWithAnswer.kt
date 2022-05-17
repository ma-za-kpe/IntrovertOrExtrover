package com.maku.extrointrovert.core.data.local.models

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithAnswer(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "questionId"
    )
    val answers: List<Answer>
)
