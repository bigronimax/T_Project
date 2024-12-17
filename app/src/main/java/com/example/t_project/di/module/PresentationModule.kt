package com.example.t_project.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.t_project.di.ViewModelKey
import com.example.t_project.presentation.ViewModelFactory
import com.example.t_project.presentation.jokeCreate.JokeCreateViewModel
import com.example.t_project.presentation.jokeDetails.JokeDetailsViewModel
import com.example.t_project.presentation.jokeList.JokeListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(JokeListViewModel::class)
    fun bindJokeListViewModel(viewModel: JokeListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JokeDetailsViewModel::class)
    fun bindJokeDetailsViewModel(viewModel: JokeDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JokeCreateViewModel::class)
    fun bindJokeCreateViewModel(viewModel: JokeCreateViewModel): ViewModel
}