package com.maku.extrointrovert.questionsfeature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.maku.extrointrovert.core.data.local.models.Answer

@Composable
fun QuestionsScreen(
    questionsWithAnswersViewModel: GetAllQuestionsWithAnswersViewModel,
) {
    val questionsState = questionsWithAnswersViewModel.state.observeAsState()
    Column() {
        val list = questionsState.value!!.questions
        val answers = questionsState.value!!.answers
        if (list.isNotEmpty()){
            Text(text = list[questionsState.value!!.index].question)
            AnswerRadioButtons(list[questionsState.value!!.index].questionId, answers)
            Row {
                Button(onClick = { /*TODO*/ }, enabled = false) {
                    Text(text = "${list[questionsState.value!!.index].questionId} Previous")
                }

                if (questionsState.value!!.index < list.size - 1){
                    Button(onClick = {
                        questionsWithAnswersViewModel.updateIndex(questionsState.value!!.index + 1)
                    }) {
                        Text(text = "Next")
                    }
                } else if (questionsState.value!!.index == list.size - 1){
                    Button(onClick = {
                        // go to assessment screen
                    }) {
                        Text(text = "Done")
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerRadioButtons(questionId: Long, answers: List<Answer>) {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    // val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    val items = answers.filter { ans ->
        ans.questionId == questionId
    }

    Column(Modifier.padding(8.dp)) {
        Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = isSelectedItem(item.answerId.toString()),
                    onClick = { onChangeState(item.answerId.toString()) },
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

@Composable
fun NextButton(title: String) {
    Button(onClick = {

    }) {
        Text(text = title)
    }
}