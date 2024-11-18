package com.example.t_project.ui.jokeList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import com.example.t_project.domain.reposImpl.JokesGenerationRepositoryImpl
import com.example.t_project.domain.usecases.CheckNewJokesUseCase
import com.example.t_project.domain.usecases.GenerateJokesUseCase
import com.example.t_project.domain.usecases.GetJokesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeListViewModel(
    private val generationRepository: JokesGenerationRepository,
): ViewModel() {
    private val generateJokesUseCase by lazy { GenerateJokesUseCase(jokesGenerationRepository = generationRepository)}
    private val getJokesUseCase by lazy { GetJokesUseCase(jokesGenerationRepository = generationRepository) }
    private val checkNewJokesUseCase by lazy { CheckNewJokesUseCase(jokesGenerationRepository = generationRepository) }

//    val jokesStateFlow: MutableStateFlow<List<Joke>?> = MutableStateFlow(null)
//    val progressStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val jokesLiveData = MutableLiveData<List<Joke>>()
    val progressLiveData = MutableLiveData<Boolean>()

    init {
        progressLiveData.postValue(false)
    }
    fun loadJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkNewJokesUseCase.execute()) {
                progressLiveData.postValue(true)
                val jokesList = getJokesUseCase.execute(true)
                jokesLiveData.postValue(jokesList)
                progressLiveData.postValue(false)
            }
        }
    }

//    fun refreshPage(adapter: JokeAdapter) {
//        loadJokes()
//        jokesStateFlow.value?.let { adapter.setNewData(it) }
//    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeListViewModel(
                    generationRepository = JokesGenerationRepositoryImpl
                )
            }
        }
    }

}