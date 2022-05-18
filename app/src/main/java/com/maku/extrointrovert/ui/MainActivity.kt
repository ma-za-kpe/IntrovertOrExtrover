package com.maku.extrointrovert.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.maku.extrointrovert.core.utils.Constants
import com.maku.extrointrovert.questionsfeature.GetAllQuestionsWithAnswersViewModel
import com.maku.extrointrovert.questionsfeature.QuestionsScreen
import com.maku.extrointrovert.ui.ui.theme.ExtroIntroVertTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val index = sharedPref.getInt(Constants.SHARED_PREF_CURRENT_QN_INDEX, 0)
        Log.d("Compose MainActivity", "index $index")

        setContent {
            ExtroIntroVertTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    // check if user asked to retake
                    // also, using shared preferences, check saved question index, and update state accordingly.
                    questionsWithAnswersViewModel.updateIndex(index)
                    // in case a user leaves the app and comes back later, tere state has been saved.
                    QuestionsScreen(questionsWithAnswersViewModel, this)
                }
            }
        }
    }
}
