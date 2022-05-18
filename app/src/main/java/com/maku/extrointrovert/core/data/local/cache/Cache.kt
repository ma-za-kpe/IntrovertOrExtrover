package com.maku.extrointrovert.core.data.local.cache

import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.core.data.local.models.QuestionWithAnswer
import com.maku.extrointrovert.core.data.local.models.Trait
import io.reactivex.Flowable

interface Cache {
    fun getAllQuestions(): Flowable<List<Question>>
    fun getAllAnswers(): Flowable<List<Answer>>

    suspend fun insertTrait(trait: Trait)
    fun getAllTraits(): Flowable<List<Trait>>

    suspend fun nukeTraitsTable()
}