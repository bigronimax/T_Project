package com.example.t_project

import android.app.Application
import com.example.t_project.data.datasource.database.JokesDataBase
import com.example.t_project.di.AppComponent

class App: Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        JokesDataBase.initDatabase(this)
    }
}