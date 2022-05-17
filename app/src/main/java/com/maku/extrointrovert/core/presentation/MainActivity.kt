package com.maku.extrointrovert.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maku.extrointrovert.R
import com.maku.extrointrovert.questionsfeature.ExtroIntroVertFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ExtroIntroVertFragment.newInstance())
                .commitNow()
        }
    }
}