package com.example.myapplication

import android.app.Application
import android.content.Context

class MyApplication : Application() {

//  알람에서 어플 context 정보 받기 위해 만듬.

    init{
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

}