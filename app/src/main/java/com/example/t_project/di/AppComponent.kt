package com.example.t_project.di

import android.content.Context
import com.example.t_project.MainActivity
import com.example.t_project.di.module.DataModule
import com.example.t_project.presentation.jokeCreate.JokeCreateFragment
import com.example.t_project.presentation.jokeDetails.JokeDetailsFragment
import com.example.t_project.presentation.jokeDetails.JokeDetailsViewModel
import com.example.t_project.presentation.jokeList.JokeListFragment
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

    fun inject(fragment: JokeListFragment)
    fun inject(fragment: JokeCreateFragment)
    fun inject(fragment: JokeDetailsFragment)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}