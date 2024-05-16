package com.bayu07750.albumsplaceholderkmp

import android.app.Application

class MyApp : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}