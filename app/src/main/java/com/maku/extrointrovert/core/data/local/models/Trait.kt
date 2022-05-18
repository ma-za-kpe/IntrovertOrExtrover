package com.maku.extrointrovert.core.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "traits")
data class Trait(
    val question: String,
    val answer: String,
    val score: String
) {
    @PrimaryKey(autoGenerate = true)
    var traitId: Int = 0
}
