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
            Question(1, "You’re really busy at work and a colleague is telling you their life story and personal woes. You: "),
            Question(2, "You’ve been sitting in the doctor’s waiting room for more than 25 minutes. You:"),
            Question(3, "You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You: "),
            Question(4, "You are taking part in a guided tour of a museum. You: ")
        )
    }
}

