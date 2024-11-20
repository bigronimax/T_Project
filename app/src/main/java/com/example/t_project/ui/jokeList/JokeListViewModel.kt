package com.example.t_project.ui.jokeList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.domain.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.usecases.generationRepository.GetRemoteJokesUseCase
import com.example.t_project.domain.usecases.generationRepository.GenerateJokesUseCase
import com.example.t_project.domain.usecases.generationRepository.GetLocalJokesUseCase
import com.example.t_project.domain.usecases.generationRepository.LoadRemoteJokesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeListViewModel(
    private val jokesRepository: JokesRepository,
): ViewModel() {

    private val generateJokesUseCase by lazy { GenerateJokesUseCase(jokesRepository = jokesRepository) }
    private val getLocalJokesUseCase by lazy { GetLocalJokesUseCase(jokesRepository = jokesRepository) }

    private val loadRemoteJokesUseCase by lazy { LoadRemoteJokesUseCase(jokesRepository = jokesRepository) }
    private val getRemoteJokesUseCase by lazy { GetRemoteJokesUseCase(jokesRepository = jokesRepository) }

    val jokesLiveData = MutableLiveData<List<Joke>>()
    val progressLiveData = MutableLiveData<Boolean>()

    private var localJokes = listOf<Joke>()
    private var remoteJokes = listOf<Joke>()

    init {
        progressLiveData.postValue(false)
        loadRemoteJokes()
    }

    private fun postJokes() {
        jokesLiveData.postValue(localJokes + remoteJokes)
    }

    fun loadLocalJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            progressLiveData.postValue(true)
            localJokes = getLocalJokesUseCase.execute(true)
            progressLiveData.postValue(false)
            postJokes()
        }
    }

    fun loadRemoteJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            progressLiveData.postValue(true)
            loadRemoteJokesUseCase.execute()
            remoteJokes = getRemoteJokesUseCase.execute(delay=true)
            progressLiveData.postValue(false)
            postJokes()
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeListViewModel(
                    jokesRepository = JokesRepositoryImpl,
                )
            }
        }
    }

}