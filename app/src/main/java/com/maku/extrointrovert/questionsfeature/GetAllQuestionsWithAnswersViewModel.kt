package com.maku.extrointrovert.questionsfeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.questionsfeature.usecases.GetAllAnswers
import com.maku.extrointrovert.questionsfeature.usecases.GetAllQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class GetAllQuestionsWithAnswersViewModel @Inject constructor(
    private val getAllQuestions: GetAllQuestions,
    private val getAllAnswers: GetAllAnswers,
    private val compositeDisposable: CompositeDisposable
): ViewModel() {

    val question: LiveData<List<Question>?> get() = _question
    private val _question = MutableLiveData<List<Question>?>()

    val state: LiveData<QuestionsViewState> get() = _state
    private val _state = MutableLiveData<QuestionsViewState>()

    init {
        _state.value = QuestionsViewState()
        subscribeToQuestionUpdates()
        subscribeToAnswerUpdates()
    }

    private fun subscribeToAnswerUpdates() {
        getAllAnswers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onAnswersList(it) }
            .addTo(compositeDisposable)
    }

    private fun onAnswersList(it: List<Answer>?) {
        _state.value = state.value!!.copy(answers = it!!)
    }

    private fun subscribeToQuestionUpdates() {
        getAllQuestions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onQuestionsList(it)
            }
            .addTo(compositeDisposable)
    }

    private fun onQuestionsList(it: List<Question>?) {
        _question.value = it
        _state.value = state.value!!.copy(questions = it!!)
    }

    fun updateIndex(index: Int) {
        _state.value = state.value!!.updateIndex(index)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}