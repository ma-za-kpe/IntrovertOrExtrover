package com.maku.extrointrovert.core.data.repository

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.Trait
import io.reactivex.Flowable

interface Repo {
    fun getAllQuestions(): Flowable<List<Question>>
    fun getAllAnswers(): Flowable<List<Answer>>

    suspend fun insertTrait(trait: Trait)
    fun getAllTraits(): Flowable<List<Trait>>
}