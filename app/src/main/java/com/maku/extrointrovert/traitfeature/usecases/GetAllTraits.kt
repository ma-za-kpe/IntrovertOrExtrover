package com.maku.extrointrovert.traitfeature.usecases

import com.maku.extrointrovert.core.data.repository.Repo
import javax.inject.Inject

class GetAllTraits @Inject constructor(
    private val repo: Repo
) {
    operator fun invoke() = repo.getAllTraits()
}