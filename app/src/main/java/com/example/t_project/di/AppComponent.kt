package com.example.t_project.di

import android.content.Context
import com.example.t_project.MainActivity
import com.example.t_project.di.module.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DataModule::class,
    ]
)

interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}