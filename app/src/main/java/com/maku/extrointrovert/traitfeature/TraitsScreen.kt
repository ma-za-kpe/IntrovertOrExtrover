package com.maku.extrointrovert.traitfeature

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.maku.extrointrovert.core.utils.Constants
import com.maku.extrointrovert.questionsfeature.GetAllQuestionsWithAnswersViewModel
import com.maku.extrointrovert.core.presentation.MainActivity
import com.maku.extrointrovert.ui.router.BackButtonHandler
import com.maku.extrointrovert.ui.router.Screen
import com.maku.extrointrovert.ui.router.TraitsRouter

@Composable
fun TraitsScreen(
    traitViewModel: TraitViewModel,
    mainActivity: MainActivity,
    questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel
) {
    val trait = traitViewModel.trait.observeAsState().value
    Column() {
            Text(text = "$trait")
            // Text(text = "${trait?.get(0)?.question}")
            Button(onClick = {
                // 1. we clear traits table
                traitViewModel.deleteAll()
                // 2. we set shared preferences done to retake and index to 0
                onSaveCurrentQuestionIndex(mainActivity)
                onSaveDone(mainActivity)
                // 3. set screen state to survey, set index = 0, selected = "
                questionsWithAnswersViewModel.updateIndex(0)
                questionsWithAnswersViewModel.updateSelectedValue("")
                TraitsRouter.navigateTo(Screen.Questions)
            }) {
                Text(text = "Retake")
            }

    }

    BackButtonHandler {
        TraitsRouter.navigateTo(Screen.Questions)
    }
}

private fun onSaveCurrentQuestionIndex(mainActivity: MainActivity) {
    val sharedPref = mainActivity.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putInt(Constants.SHARED_PREF_CURRENT_QN_INDEX, 0)
    editor.apply()
}

private fun onSaveDone(mainActivity: MainActivity) {
    val sharedPref = mainActivity.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putString(Constants.SHARED_PREF_DONE, "retake")
    editor.apply()
}