package com.example.t_project.ui.jokeCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import com.example.t_project.domain.reposImpl.JokesGenerationRepositoryImpl
import com.example.t_project.domain.usecases.AddNewJokeUseCase
import com.example.t_project.domain.usecases.GetJokesUseCase

class JokeCreateViewModel(
    private val generationRepository: JokesGenerationRepository
): ViewModel() {
    private val addJokeUseCase by lazy { AddNewJokeUseCase(jokesGenerationRepository = generationRepository) }
    private val getJokesUseCase by lazy { GetJokesUseCase(jokesGenerationRepository = generationRepository) }

    suspend fun addNewJoke(joke: Joke) {
        return addJokeUseCase.execute(joke)
    }

    suspend fun getJokesSize(): Int {
        return getJokesUseCase.execute(false).size
    }
    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeCreateViewModel(
                    generationRepository = JokesGenerationRepositoryImpl,
                )
            }
        }
    }
}