package com.maku.extrointrovert.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maku.extrointrovert.core.data.local.daos.ExtroIntroVertDao
import com.maku.extrointrovert.core.data.local.models.Answer
import com.maku.extrointrovert.core.data.local.models.Question

@Database(
    entities = [
        Question::class,
        Answer::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class ExtroIntroVertDB : RoomDatabase() {
    abstract fun extroIntroVertDao(): ExtroIntroVertDao
}
