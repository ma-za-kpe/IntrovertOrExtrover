package com.maku.extrointrovert.traitfeature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.extrointrovert.core.data.local.models.Trait
import com.maku.extrointrovert.core.data.repository.RepoImpl
import com.maku.extrointrovert.questionsfeature.QuestionsViewState
import com.maku.extrointrovert.traitfeature.usecases.GetAllTraits
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TraitViewModel @Inject constructor(
    private val getAllTraits: GetAllTraits,
    // TODO: must clean this up with a use case... we cannot have a repo here
    private val repoImpl: RepoImpl,
    private val compositeDisposable: CompositeDisposable
): ViewModel() {
    val trait: LiveData<List<Trait>> get() = _trait
    private val _trait = MutableLiveData<List<Trait>>()

    val state: LiveData<QuestionsViewState> get() = _state
    private val _state = MutableLiveData<QuestionsViewState>()

    init {
        _state.value = QuestionsViewState()
        subscribeToAllTraitsUpdates()
    }

    fun deleteAll(){
        viewModelScope.launch {
            repoImpl.nukeTraitsTable()
        }
    }

    fun updateScreenState(ss: String) {
        _state.value = state.value!!.updateScreenState(ss)
    }

    private fun subscribeToAllTraitsUpdates() {
         getAllTraits()
             .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onAllTraitsList(it) }
            .addTo(compositeDisposable)
    }

    private fun onAllTraitsList(it: List<Trait>) {
        Log.d("vm", "trait $it")
        _trait.value = it
    }

    fun insertTrait(trait: Trait){
            viewModelScope.launch {
                repoImpl.insertTrait(trait)
            }
        }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
