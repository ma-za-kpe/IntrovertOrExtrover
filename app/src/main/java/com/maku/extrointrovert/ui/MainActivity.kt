package com.maku.extrointrovert.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.maku.extrointrovert.questionsfeature.GetAllQuestionsWithAnswersViewModel
import com.maku.extrointrovert.questionsfeature.QuestionsScreen
import com.maku.extrointrovert.ui.ui.theme.ExtroIntroVertTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExtroIntroVertTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    QuestionsScreen(questionsWithAnswersViewModel)
                }
            }
        }
    }
}
