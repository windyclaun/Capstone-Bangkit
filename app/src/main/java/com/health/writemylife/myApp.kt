package com.health.writemylife

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class myApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}