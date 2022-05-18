package com.maku.extrointrovert.ui

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maku.extrointrovert.questionsfeature.GetAllQuestionsWithAnswersViewModel
import com.maku.extrointrovert.questionsfeature.QuestionsScreen
import com.maku.extrointrovert.traitfeature.TraitViewModel
import com.maku.extrointrovert.traitfeature.TraitsScreen
import com.maku.extrointrovert.ui.router.Screen
import com.maku.extrointrovert.ui.router.TraitsRouter

@Composable
fun TraitsApp(
  questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel,
  mainActivity: MainActivity,
  traitViewModel: TraitViewModel
) {

  val questionsState = questionsWithAnswersViewModel.state.observeAsState()
  val selectedValue = remember { mutableStateOf("") }
  val scoreValue = remember { mutableStateOf("") }
  val screenState = traitViewModel.state.value?.survey

  Surface(color = MaterialTheme.colors.background) {
    Crossfade(targetState = TraitsRouter.currentScreen) { screenState ->
      when (screenState.value) {
        is Screen.Questions ->QuestionsScreen(questionsWithAnswersViewModel, mainActivity, traitViewModel, scoreValue, selectedValue, questionsState)
        is Screen.Traits -> TraitsScreen(traitViewModel, mainActivity, questionsWithAnswersViewModel)
      }
    }
  }
}