package com.maku.extrointrovert.questionsfeature

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.utils.Constants
import com.maku.extrointrovert.ui.MainActivity

@Composable
fun QuestionsScreen(
    questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel,
    mainActivity: MainActivity,
) {
    val questionsState = questionsWithAnswersViewModel.state.observeAsState()
    val selectedValue = remember { mutableStateOf("") }

    Column() {
        val list = questionsState.value!!.questions
        val answers = questionsState.value!!.answers
        if (list.isNotEmpty()){
            Text(text = list[questionsState.value!!.index].question)
            AnswerRadioButtons(list[questionsState.value!!.index].questionId, answers,
                questionsWithAnswersViewModel, selectedValue)
            Row {
                Button(onClick = { /*TODO*/ }, enabled = false) {
                    Text(text = "Previous")
                }

                if (questionsState.value!!.index < list.size - 1){
                    Button(onClick = {
                        // 1. reset selected value to empty
                        // 2. save index in shared preference
                        // 3. change state to next question
                        selectedValue.value = ""
                        onSaveCurrentQuestionIndex(mainActivity, questionsState.value!!.index)
                        questionsWithAnswersViewModel.updateIndex(questionsState.value!!.index + 1)
                    }, enabled = questionsState.value!!.enableButton) {
                        Text(text = "Next")
                    }
                } else if (questionsState.value!!.index == list.size - 1){
                    Button(onClick = {
                        // go to assessment screen
                        // first save current screen, in case a user leaves the app.
                        onSaveCurrentQuestionIndex(mainActivity, questionsState.value!!.index)

                    }, enabled = questionsState.value!!.enableButton) {
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
    selectedValue: MutableState<String>
) {
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    val items = answers.filter { ans ->
        ans.questionId == questionId
    }

    Column(Modifier.padding(8.dp)) {
        if (selectedValue.value.isEmpty()){
            // 1. set state to disable the button
            Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
            questionsWithAnswersViewModel.updateButtonEnabled(false)
        } else {
            // save value id in list(shared preference)
            // set selectedValue state to empty string
            // enable the button to go to next item
            Text(text = "Selected value is: ${selectedValue.value.ifEmpty { "NONE" }}")
            questionsWithAnswersViewModel.updateButtonEnabled(true)
        }
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = isSelectedItem(item.answerId.toString()),
                    onClick = {
                        onChangeState(item.answerId.toString())
                              },
                    role = Role.RadioButton
                ).padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item.answerId.toString()),
                    onClick = null
                )
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

@Composable
fun NextButton(title: String) {
    Button(onClick = {

    }) {
        Text(text = title)
    }
}