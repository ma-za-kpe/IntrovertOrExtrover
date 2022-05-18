package com.maku.extrointrovert.core.data.local.cache

import com.maku.extrointrovert.core.data.local.daos.ExtroIntroVertDao
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.QuestionWithAnswer
import com.maku.extrointrovert.core.data.local.models.Trait
import io.reactivex.Flowable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class RoomCache @Inject constructor(
    private val extroIntroVertDao: ExtroIntroVertDao,
) : Cache {

     init {
        initDatabase()
    }

    private fun initDatabase() {
        GlobalScope.launch {
            // Prepopulate questions
            val questions = Question.DEFAULT_QUESTIONS.toTypedArray()
            val answers = Answer.DEFAULT_ANSWERS.toTypedArray()

            if (extroIntroVertDao.getQuestionSync().isEmpty()) {
                // insert all questions and answers
                 extroIntroVertDao.insertAllQuestions(*questions)
            }

            if (extroIntroVertDao.getAnswerSync().isEmpty()) {
                // insert all answers
                extroIntroVertDao.insertAllAnswers(*answers)
            }
        }
    }

    override fun getAllQuestions(): Flowable<List<Question>> {
       return extroIntroVertDao.getAllQuestion()
    }

    override fun getAllAnswers(): Flowable<List<Answer>> {
        return extroIntroVertDao.getAllAnswers()
    }

    override suspend fun insertTrait(trait: Trait) {
        extroIntroVertDao.insertTrait(trait)
    }

    override fun getAllTraits(): Flowable<List<Trait>> {
        return extroIntroVertDao.getAllTraits()
    }

}