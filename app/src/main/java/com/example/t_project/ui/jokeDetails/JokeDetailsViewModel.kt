package com.example.t_project.ui.jokeDetails

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.domain.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.usecases.generationRepository.GetLocalJokeItemUseCase
import com.example.t_project.domain.usecases.generationRepository.GetLocalJokesUseCase
import com.example.t_project.domain.usecases.generationRepository.GetRemoteJokesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeDetailsViewModel(
    private val jokesRepository: JokesRepository
): ViewModel() {
    private val getLocalJokeItemUseCase by lazy { GetLocalJokeItemUseCase(jokesRepository = jokesRepository) }
    private val getLocalJokesUseCase by lazy {GetLocalJokesUseCase(jokesRepository = jokesRepository)}
    private val getRemoteJokesUseCase by lazy { GetRemoteJokesUseCase(jokesRepository = jokesRepository) }

    val jokesLiveData = MutableLiveData<Joke>()
    var colorLiveData = MutableLiveData<Int?>()

    fun getJokeItem(jokeId: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (jokeId == "-1") {
                Toast.makeText(context, "Invalid joke data!", Toast.LENGTH_SHORT).show()
            } else {
                jokesLiveData.postValue(getLocalJokeItemUseCase.execute(jokeId))
            }
        }
    }

    fun getBackgroundColor(jokeId: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (getLocalJokesUseCase.execute(false).filter { it.id == jokeId }.size == 1) {
                colorLiveData.postValue(Color.BLUE)
            } else if (getRemoteJokesUseCase.execute(false).filter { it.id == jokeId }.size == 1) {
                colorLiveData.postValue(Color.BLACK)
            } else {
                Toast.makeText(context, "Invalid joke data!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeDetailsViewModel(
                    jokesRepository = JokesRepositoryImpl,
                )
            }
        }
    }

}