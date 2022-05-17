package com.maku.extrointrovert.core.data.repository

import com.maku.extrointrovert.core.data.local.cache.Cache
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.QuestionWithAnswer
import io.reactivex.Flowable
import javax.inject.Inject

class RepoImpl @Inject constructor(
private val cache: Cache
) : Repo {
    override fun getAllQuestions(): Flowable<List<Question>> {
        return cache.getAllQuestions()
    }

    override fun getAllAnswers(): Flowable<List<Answer>> {
        return cache.getAllAnswers()
    }
}