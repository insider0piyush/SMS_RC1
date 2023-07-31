package com.insider0piyush.sms_rc1

import android.app.Application
import com.google.android.material.color.DynamicColors

class DynamicColors : Application() {
    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()
    }
}