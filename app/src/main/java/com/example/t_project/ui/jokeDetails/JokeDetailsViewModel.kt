package com.example.t_project.ui.jokeDetails

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import com.example.t_project.domain.reposImpl.JokesGenerationRepositoryImpl
import com.example.t_project.domain.usecases.GetJokesUseCase

class JokeDetailsViewModel(
    private val generationRepository: JokesGenerationRepository
): ViewModel() {
    private val getJokesUseCase by lazy { GetJokesUseCase(jokesGenerationRepository = generationRepository) }

    suspend fun getJokeItem(jokeId: Int) : Joke {
        return getJokesUseCase.execute()[jokeId]
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeDetailsViewModel(
                    generationRepository = JokesGenerationRepositoryImpl,
                )
            }
        }
    }

}