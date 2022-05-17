package com.maku.extrointrovert.core.data.local.cache

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.QuestionWithAnswer
import io.reactivex.Flowable

interface Cache {
    fun getAllQuestions(): Flowable<List<Question>>
    fun getAllAnswers(): Flowable<List<Answer>>
}