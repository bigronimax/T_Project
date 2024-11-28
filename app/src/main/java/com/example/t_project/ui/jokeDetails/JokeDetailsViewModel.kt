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
import com.example.t_project.domain.usecases.generationRepository.GetJokeItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeDetailsViewModel(
    private val jokesRepository: JokesRepository
): ViewModel() {
    private val getJokeItemUseCase by lazy { GetJokeItemUseCase(jokesRepository = jokesRepository) }

    val jokesLiveData = MutableLiveData<Joke>()
    var colorLiveData = MutableLiveData<Int>()

    fun getJokeItem(jokeId: String?, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (jokeId == null) {
                Toast.makeText(context, "Invalid joke data!", Toast.LENGTH_SHORT).show()
            } else {
                jokesLiveData.postValue(getJokeItemUseCase.execute(jokeId))
                getBackgroundColor(jokeId)
            }
        }
    }

    private fun getBackgroundColor(jokeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (getJokeItemUseCase.execute(jokeId)!!.source) {
                Joke.SourceEnum.LOCAL -> {
                    colorLiveData.postValue(Color.BLUE)
                }
                Joke.SourceEnum.REMOTE -> {
                    colorLiveData.postValue(Color.BLACK)
                }
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