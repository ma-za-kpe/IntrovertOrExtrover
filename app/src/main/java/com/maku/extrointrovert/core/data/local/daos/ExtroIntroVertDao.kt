package com.maku.extrointrovert.core.data.local.daos

import androidx.room.*
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.QuestionWithAnswer
import io.reactivex.Flowable

@Dao
interface ExtroIntroVertDao {
//    @Transaction
//    @Query("SELECT * FROM questions")
//    fun getQuestionWithAnswer(): Flowable<List<QuestionWithAnswer>>
//

    @Query("SELECT * FROM questions")
    fun getAllQuestion(): Flowable<List<Question>>

    @Query("SELECT * FROM questions")
    fun getQuestionSync():List<Question>

    @Query("SELECT * FROM answers")
    fun getAllAnswers(): Flowable<List<Answer>>

    @Query("SELECT * FROM answers")
    fun getAnswerSync():List<Answer>

    @Insert
    fun insertAllQuestions(vararg question: Question)

    @Insert
    fun insertAllAnswers(vararg answer: Answer)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertQuestionsWithAnswers(
//        question: Question,
//        answers: List<Answer>
//    )
//
//    suspend fun insertQuestionWithAnswers(qa: List<QuestionWithAnswer>) {
//        for (qas in qa) {
//            insertQuestionsWithAnswers(
//                qas.question,
//                qas.answers
//            )
//        }
//    }
}