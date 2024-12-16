package com.example.t_project

import android.app.Application
import com.example.t_project.data.datasource.database.JokesDataBase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        JokesDataBase.initDatabase(this)
    }
}