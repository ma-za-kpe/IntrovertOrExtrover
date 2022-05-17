package com.maku.extrointrovert

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ExtroIntroVertApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant()
    }
}