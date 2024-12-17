package com.example.t_project.presentation.jokeCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.data.datasource.database.JokesDataBase
import com.example.t_project.data.datasource.internet.ApiDataSource
import com.example.t_project.domain.entity.Joke
import com.example.t_project.data.mapper.JokeMapper
import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.data.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.usecases.jokesRepository.AddNewJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class JokeCreateViewModel @Inject constructor(
    private val generationRepository: JokesRepository
): ViewModel() {
    private val addJokeUseCase by lazy { AddNewJokeUseCase(jokesRepository = generationRepository) }

    fun addNewJoke(question: String, answer: String, category: String, source: Joke.SourceEnum) {
        viewModelScope.launch(Dispatchers.IO) {
            addJokeUseCase.execute(question, answer, category, source)
        }
    }
    companion object {
//        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                JokeCreateViewModel(
//                    generationRepository = JokesRepositoryImpl(
//                        ApiDataSource(),
//                        JokesDataBase.INSTANCE.jokeDao(),
//                        JokeMapper()
//                    ),
//                )
//            }
//        }
    }
}