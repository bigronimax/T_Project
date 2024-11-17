package com.example.t_project.ui.jokeCreate

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import com.example.t_project.domain.reposImpl.JokesGenerationRepositoryImpl
import com.example.t_project.domain.usecases.GetJokesUseCase
import com.example.t_project.domain.usecases.SetNewJokeUseCase
import com.example.t_project.ui.jokeDetails.JokeDetailsViewModel

class JokeCreateViewModel(
    private val generationRepository: JokesGenerationRepository
): ViewModel() {
    private val setJokeUseCase by lazy { SetNewJokeUseCase(jokesGenerationRepository = generationRepository) }

    suspend fun setNewJoke(joke: Joke) {
        return setJokeUseCase.execute(joke)
    }
    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeCreateViewModel(
                    generationRepository = JokesGenerationRepositoryImpl,
                )
            }
        }
    }
}