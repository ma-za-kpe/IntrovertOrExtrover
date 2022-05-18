package com.maku.extrointrovert.core.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.maku.extrointrovert.R
import com.maku.extrointrovert.questionsfeature.GetAllQuestionsWithAnswersViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: GetAllQuestionsWithAnswersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[GetAllQuestionsWithAnswersViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewStateUpdates()
        //requestExtroIntroVertList()
    }

    private fun observeViewStateUpdates() {
        viewModel.state.observe(viewLifecycleOwner) {
            // update the fragments state
            Timber.d("onQuestionsAndAnswersList ${it.questions}")
        }
    }

    private fun requestExtroIntroVertList() {
        TODO("Not yet implemented")
    }


}