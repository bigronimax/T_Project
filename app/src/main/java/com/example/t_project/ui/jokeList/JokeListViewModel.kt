package com.example.t_project.ui.jokeList

import androidx.lifecycle.MutableLiveData
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
import com.example.t_project.domain.usecases.jokesRepository.LoadJokesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeListViewModel(
    private val jokesRepository: JokesRepository,
): ViewModel() {

    private val loadJokesUseCase by lazy { LoadJokesUseCase(jokesRepository = jokesRepository) }

    val jokesLiveData = MutableLiveData<List<Joke>>()
    val progressLiveData = MutableLiveData<Boolean>(false)

    init {
        loadJokes(true, true)
    }

    fun loadJokes(remoteLoad: Boolean = true, internet: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            progressLiveData.postValue(true)
            val jokes = loadJokesUseCase.execute(remoteLoad, internet)
            progressLiveData.postValue(false)
            jokesLiveData.postValue(jokes)
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeListViewModel(
                    jokesRepository = JokesRepositoryImpl(
                        ApiDataSource(),
                        JokesDataBase.INSTANCE.jokeDao(),
                        JokeMapper()
                    ),
                )
            }
        }
    }

}