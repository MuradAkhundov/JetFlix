package com.muradakhundov.jetflix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetflixApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}