package com.example.the_hotel

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.utilities.DynamicColor

class myApp  : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}