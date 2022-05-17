package com.maku.extrointrovert.core.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey val questionId: Long,
    val question: String
    ) {
    companion object {
        val DEFAULT_QUESTIONS = listOf(
            Question(1, "You’re really busy at work and a colleague is telling you their life story and personal woes. You")
        )
    }
}

