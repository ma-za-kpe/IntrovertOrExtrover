package com.maku.extrointrovert.core.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.maku.extrointrovert.core.data.local.models.Question
import com.maku.extrointrovert.questionsfeature.GetAllQuestionsWithAnswersViewModel
import com.maku.extrointrovert.questionsfeature.QuestionsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppIntro() {
    private lateinit var viewModel: GetAllQuestionsWithAnswersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model: GetAllQuestionsWithAnswersViewModel by viewModels()

        model.question.observe(this) {
            Log.d("MainActivity", "list $it")
        }
        addSlide(QuestionsFragment.newInstance(
            title = "kkkkkkkkkkk",
            description = "This is the first slide of the example"
        ))

        addSlide(QuestionsFragment.newInstance(
            title = "Welcome...",
            description = "This is the first slide of the example"
        ))

        addSlide(QuestionsFragment.newInstance(
            title = "...Let's get started!",
            description = "This is the last slide, I won't annoy you more :)"
        ))
    }

    // user is not allowed to skip
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // go to results screen
        finish()
    }
}