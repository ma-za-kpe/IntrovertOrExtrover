package com.maku.extrointrovert.core.data.repository

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import io.reactivex.Flowable

interface Repo {
    fun getAllQuestions(): Flowable<List<Question>>
    fun getAllAnswers(): Flowable<List<Answer>>
}