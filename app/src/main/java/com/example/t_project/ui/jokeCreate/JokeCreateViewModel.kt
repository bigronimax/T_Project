package com.example.t_project.ui.jokeCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.database.JokesDataBase
import com.example.t_project.domain.internet.ApiDataSource
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.models.JokeMapper
import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.domain.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.usecases.jokesRepository.AddNewJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeCreateViewModel(
    private val generationRepository: JokesRepository
): ViewModel() {
    private val addJokeUseCase by lazy { AddNewJokeUseCase(jokesRepository = generationRepository) }

    fun addNewJoke(question: String, answer: String, category: String, source: Joke.SourceEnum) {
        viewModelScope.launch(Dispatchers.IO) {
            addJokeUseCase.execute(question, answer, category, source)
        }
    }
    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeCreateViewModel(
                    generationRepository = JokesRepositoryImpl(
                        ApiDataSource(),
                        JokesDataBase.INSTANCE.jokeDao(),
                        JokeMapper()
                    ),
                )
            }
        }
    }
}