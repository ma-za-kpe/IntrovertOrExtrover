package com.maku.extrointrovert.questionsfeature

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Trait
import com.maku.extrointrovert.core.utils.Constants
import com.maku.extrointrovert.traitfeature.TraitViewModel
import com.maku.extrointrovert.core.presentation.MainActivity
import com.maku.extrointrovert.ui.router.Screen
import com.maku.extrointrovert.ui.router.TraitsRouter

@Composable
fun QuestionsScreen(
    questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel,
    mainActivity: MainActivity,
    traitViewModel: TraitViewModel,
    scoreValue: MutableState<String>,
    selectedValue: MutableState<String>,
    questionsState: State<QuestionsViewState?>,
    modifier: Modifier,
) {

    Column(modifier.padding(12.dp)) {
        val list = questionsState.value!!.questions
        val answers = questionsState.value!!.answers

        if (list.isNotEmpty()){

            Text(
                text = "Qestion ${list[questionsState.value!!.index].questionId} / 4",
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign= TextAlign.Start,
            )
            Spacer(modifier = modifier.size(5.dp))
            Text(
                text = list[questionsState.value!!.index].question,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign= TextAlign.Start,
            )
            Spacer(modifier = modifier.size(5.dp))
            Text(
                text = "You must select at least one option.",
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign= TextAlign.Start,
            )

            AnswerRadioButtons(list[questionsState.value!!.index].questionId, answers,
                questionsWithAnswersViewModel, selectedValue, scoreValue, modifier)
            Row(
                horizontalArrangement = Arrangement.End
            ) {

                if (questionsState.value!!.index < list.size - 1){
                    Button(onClick = {
                        // 0. update room db trait table
                        // 1. reset selected value to empty
                        // 2. save index in shared preference, set index to the next question state, to avoid saving the same trail twice
                        // 3. change state to next question
                        traitViewModel.insertTrait(Trait(list[questionsState.value!!.index].question,
                        selectedValue.value, scoreValue.value ))

                        selectedValue.value = ""
                        onSaveCurrentQuestionIndex(mainActivity, questionsState.value!!.index + 1)
                        questionsWithAnswersViewModel.updateIndex(questionsState.value!!.index + 1)
                    }, modifier.fillMaxWidth(), enabled = questionsState.value!!.enableButton) {
                        Text(text = "Next Question")
                    }
                } else if (questionsState.value!!.index == list.size - 1){
                    Button(onClick = {
                        // 1. first save current screen, in case a user leaves the app abruptly.
                        // 2. update share preference to done
                        // 3. set screen state to survey
                        // 4. go to personality trait screen
                        selectedValue.value = ""
                        onSaveCurrentQuestionIndex(mainActivity, questionsState.value!!.index)
                        onSaveDone(mainActivity)
                        TraitsRouter.navigateTo(Screen.Traits)
                    }, modifier.fillMaxWidth(), enabled = questionsState.value!!.enableButton) {
                        Text(text = "Done")
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerRadioButtons(
    questionId: Long,
    answers: List<Answer>,
    questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel,
    selectedValue: MutableState<String>,
    scoreValue: MutableState<String>,
    modifier: Modifier
) {
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    val onScoreState: (String) -> Unit = { scoreValue.value = it }
    val items = answers.filter { ans ->
        ans.questionId == questionId
    }

    Column(Modifier.padding(8.dp)) {
        if (selectedValue.value.isEmpty()){
            // 1. set state to disable the button
            questionsWithAnswersViewModel.updateButtonEnabled(false)
        } else {
            // enable the button to go to next item
            // Text(text = "Selected value is: ${selectedValue.value.ifEmpty { "NONE" }}")
            questionsWithAnswersViewModel.updateButtonEnabled(true)
        }
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item.answer),
                        onClick = {
                            onScoreState(item.score)
                            onChangeState(item.answer)
                        },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item.answer),
                    onClick = null
                )
                Spacer(modifier = modifier.size(5.dp))
                Text(
                    text = item.answer,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

private fun onSaveCurrentQuestionIndex(mainActivity: MainActivity, index: Int) {
    val sharedPref = mainActivity.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putInt(Constants.SHARED_PREF_CURRENT_QN_INDEX, index)
    editor.apply()
}

private fun onSaveDone(mainActivity: MainActivity) {
    val sharedPref = mainActivity.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putString(Constants.SHARED_PREF_DONE, "done")
    editor.apply()
}

@Composable
fun NextButton(title: String) {
    Button(onClick = {

    }) {
        Text(text = title)
    }
}