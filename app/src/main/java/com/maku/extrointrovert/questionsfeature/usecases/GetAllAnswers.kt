package com.maku.extrointrovert.questionsfeature.usecases

import com.maku.extrointrovert.core.data.repository.Repo
import javax.inject.Inject

class GetAllAnswers @Inject constructor(
    private val repo: Repo
) {
    operator fun invoke() = repo.getAllAnswers()
}