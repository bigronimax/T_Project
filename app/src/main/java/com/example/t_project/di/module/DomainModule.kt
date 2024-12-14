package com.example.t_project.di.module

import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.domain.usecases.jokesRepository.AddNewJokeUseCase
import com.example.t_project.domain.usecases.jokesRepository.GetJokeItemUseCase
import com.example.t_project.domain.usecases.jokesRepository.LoadJokesUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideAddNewJokeUseCase(repository: JokesRepository): AddNewJokeUseCase {
        return AddNewJokeUseCase(repository)
    }

    @Provides
    fun provideGetJokeItemUseCase(repository: JokesRepository): GetJokeItemUseCase {
        return GetJokeItemUseCase(repository)
    }

    @Provides
    fun provideLoadJokesUseCase(repository: JokesRepository): LoadJokesUseCase {
        return LoadJokesUseCase(repository)
    }
}