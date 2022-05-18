package com.maku.extrointrovert.traitfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.extrointrovert.core.data.local.models.Trait
import com.maku.extrointrovert.core.data.repository.RepoImpl
import com.maku.extrointrovert.questionsfeature.usecases.GetAllQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TraitViewModel @Inject constructor(
    private val getAllQuestions: GetAllQuestions,
    // TODO: must clean this up with a use case... we cannot have a repo here
    private val repoImpl: RepoImpl
    ): ViewModel() {

        fun insertTrait(trait: Trait){
            viewModelScope.launch {
                repoImpl.insertTrait(trait)
            }
        }

    }
