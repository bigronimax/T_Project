package com.example.t_project.ui.jokeList

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import com.example.t_project.domain.reposImpl.JokesGenerationRepositoryImpl
import com.example.t_project.domain.usecases.GenerateJokesUseCase
import com.example.t_project.domain.usecases.GetJokesUseCase
import com.example.t_project.tools.recycler.JokeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class JokeListViewModel(
    private val generationRepository: JokesGenerationRepository,
): ViewModel() {
    private val generateJokesUseCase by lazy { GenerateJokesUseCase(jokesGenerationRepository = generationRepository)}
    private val getJokesUseCase by lazy { GetJokesUseCase(jokesGenerationRepository = generationRepository) }

//    val jokesStateFlow: MutableStateFlow<List<Joke>?> = MutableStateFlow(null)
//    val progressStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val mutableJokes = MutableLiveData<List<Joke>>()
    val mutableProgress = MutableLiveData<Boolean>()
    val mutableEmpty = MutableLiveData<Boolean>()

    init {
        mutableEmpty.postValue(true)
        mutableProgress.postValue(false)
    }
    fun loadJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            val jokesList = getJokesUseCase.execute()
            if (jokesList.isNotEmpty() && jokesList != mutableJokes.value) {
                mutableProgress.postValue(true)
                mutableEmpty.postValue(false)
                delay(2000)
                mutableJokes.postValue(jokesList)
                mutableProgress.postValue(false)
            }
        }
    }

//    fun refreshPage(adapter: JokeAdapter) {
//        loadJokes()
//        jokesStateFlow.value?.let { adapter.setNewData(it) }
//    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeListViewModel(
                    generationRepository = JokesGenerationRepositoryImpl
                )
            }
        }
    }

}