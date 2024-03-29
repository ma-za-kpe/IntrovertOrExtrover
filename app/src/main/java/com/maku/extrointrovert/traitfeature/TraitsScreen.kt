package com.maku.extrointrovert.traitfeature

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maku.extrointrovert.core.presentation.MainActivity
import com.maku.extrointrovert.core.utils.Constants
import com.maku.extrointrovert.questionsfeature.GetAllQuestionsWithAnswersViewModel
import com.maku.extrointrovert.ui.router.BackButtonHandler
import com.maku.extrointrovert.ui.router.Screen
import com.maku.extrointrovert.ui.router.TraitsRouter

@Composable
fun TraitsScreen(
    traitViewModel: TraitViewModel,
    mainActivity: MainActivity,
    questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel,
    modifier: Modifier
) {
    val trait = traitViewModel.trait.observeAsState().value
    val intovert = trait?.count { it.score == "introvert" }
    val extrovert = trait?.count { it.score == "extrovert" }

    Column(modifier.padding(8.dp)) {
        Text(text = "${trait?.get(0)?.question} ${trait?.get(0)?.answer}")
        Spacer(modifier = (modifier.size(5.dp)))
        Text(text = "${trait?.get(1)?.question} ${trait?.get(1)?.answer}")
        Spacer(modifier = (modifier.size(5.dp)))
        Text(text = "${trait?.get(2)?.question} ${trait?.get(2)?.answer}")
//            Text(text = "${trait?.get(3)?.question} ${trait?.get(3)?.answer}")
        Spacer(modifier = (modifier.size(5.dp)))
        if (intovert!! > extrovert!!){
            Text(text = "You are an introvert",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        } else {
            Text(text = "You are an extrovert",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = (modifier.size(5.dp)))
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
            }, modifier.fillMaxWidth()) {
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