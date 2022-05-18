package com.maku.extrointrovert.traitfeature

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun TraitsScreen(traitViewModel: TraitViewModel) {
    val trait = traitViewModel.trait.observeAsState().value
    Column() {
            Text(text = "$trait")
            // Text(text = "${trait?.get(0)?.question}")
            Button(onClick = {
                // 1. we clear traits table
                // 2. we set shared preferences done to retake and index to 0
                // 3.

            }) {
                Text(text = "Retake")
            }

    }
}