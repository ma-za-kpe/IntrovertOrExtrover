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
            Answer(2, 1, "Listen, but with only with half an ear", "introvert"),
            Answer(3, 1, "Interrupt and explain that you are really busy at the moment", "Extrovert"),
            Answer(5, 2, "Look at your watch every two minutes", "intorvert"),
            Answer(6, 2, "Explain to other equally impatient people in the room that the doctor is always running late", "extrovert"),
            Answer(7, 2, "Complain in a loud voice, while tapping your foot impatiently", "extrovert"),
            Answer(8, 3, "Don’t dare contradict them", "introvert"),
            Answer(9, 3, "Defend your own point of view, tooth and nail", "extrovert"),
            Answer(10, 3, "Continuously interrupt your colleague", "extrovert"),
            Answer(11, 4, "Are a bit too far towards the back so don’t really hear what the guide is saying", "introvert"),
            Answer(12, 4, "Make sure that everyone is able to hear properly", "extrovert"),
            Answer(13, 4, "Are right up the front, adding your own comments in a loud voice", "extrovert")
        )
    }
}