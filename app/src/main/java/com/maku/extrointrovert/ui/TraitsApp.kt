package com.maku.extrointrovert.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.maku.extrointrovert.core.presentation.MainActivity
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
  val modifier = Modifier

  Surface(color = MaterialTheme.colors.background) {
    Crossfade(targetState = TraitsRouter.currentScreen) { screenState ->
      Column(
        modifier = modifier
          .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        when (screenState.value) {
          is Screen.Questions ->QuestionsScreen(questionsWithAnswersViewModel, mainActivity,
          traitViewModel, scoreValue, selectedValue, questionsState, modifier)
          is Screen.Traits -> TraitsScreen(traitViewModel, mainActivity, questionsWithAnswersViewModel,
          modifier)
        }
      }
    }
  }
}
