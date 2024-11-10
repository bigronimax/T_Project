package com.example.t_project.ui.jokesList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import com.example.t_project.domain.reposImpl.JokesGenerationRepositoryImpl
import com.example.t_project.domain.usecases.GenerateJokesUseCase
import com.example.t_project.domain.usecases.GetJokesUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class JokesViewModel(
    private val generationRepository: JokesGenerationRepository
): ViewModel() {
    private val generateJokesUseCase by lazy { GenerateJokesUseCase(jokesGenerationRepository = generationRepository)}
    private val getJokesUseCase by lazy { GetJokesUseCase(jokesGenerationRepository = generationRepository) }

    val jokesStateFlow: MutableStateFlow<List<Joke>?> = MutableStateFlow(null)

    init {
        loadJokes()
    }
    private fun loadJokes() {
        val jokesList = generateJokesUseCase.execute()
        jokesStateFlow.tryEmit(jokesList)
    }

    fun getJokeItem(jokeId: Int) : Joke {
        return getJokesUseCase.execute()[jokeId]
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokesViewModel(
                    generationRepository = JokesGenerationRepositoryImpl(),
                )
            }
        }
    }

}