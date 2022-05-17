package com.maku.extrointrovert.core.data.local.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

//@Entity(
//    tableName = "answers",
//    foreignKeys = [
//        ForeignKey(
//            entity = Question::class,
//            parentColumns = ["questionId"],
//            childColumns = ["questionId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ],
//    indices = [Index("questionId")]
//)
@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey val answerId: Long,
    val questionId: Long,
    val answer: String,
    val score: String
) {
    companion object {

        val DEFAULT_ANSWERS = listOf(
            Answer(1, 1, "Dont date interrupt them", "introvert"),
            Answer(2, 1, "Dont date interrupt them", "introvert"),
            Answer(3, 1, "Dont date interrupt them", "introvert")
        )
    }
}