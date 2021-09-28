package com.example.contactapp

import android.app.Application
import android.content.Context

class ContactsApp: Application() {
    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext= applicationContext
    }
}