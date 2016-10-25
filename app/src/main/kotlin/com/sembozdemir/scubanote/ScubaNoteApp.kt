package com.sembozdemir.scubanote

import android.app.Application
import com.orhanobut.hawk.Hawk

class ScubaNoteApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Hawk 2.0
        Hawk.init(this).build()

        if (BuildConfig.CLEAR_DATA) Hawk.deleteAll()
    }
}