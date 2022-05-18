package com.maku.extrointrovert.core.data.local.daos

import androidx.room.*
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.QuestionWithAnswer
import com.maku.extrointrovert.core.data.local.models.Trait
import io.reactivex.Flowable

@Dao
interface ExtroIntroVertDao {

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

    // trait
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrait(trait: Trait)

    @Query("SELECT * FROM traits")
    fun getAllTraits(): Flowable<List<Trait>>

}