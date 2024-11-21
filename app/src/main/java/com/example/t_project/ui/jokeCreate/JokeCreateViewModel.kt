package com.example.t_project.ui.jokeCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.domain.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.usecases.generationRepository.AddNewJokeUseCase
import com.example.t_project.domain.usecases.generationRepository.GetLocalJokesUseCase

class JokeCreateViewModel(
    private val generationRepository: JokesRepository
): ViewModel() {
    private val addJokeUseCase by lazy { AddNewJokeUseCase(jokesRepository = generationRepository) }

    suspend fun addNewJoke(question: String, answer: String, category: String) {
        return addJokeUseCase.execute(question, answer, category)
    }
    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeCreateViewModel(
                    generationRepository = JokesRepositoryImpl,
                )
            }
        }
    }
}