package com.maku.extrointrovert.ui.router

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
  object Questions : Screen()
  object Traits : Screen()
}

object TraitsRouter {
  var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Questions)

  fun navigateTo(destination: Screen) {
    currentScreen.value = destination
  }
}