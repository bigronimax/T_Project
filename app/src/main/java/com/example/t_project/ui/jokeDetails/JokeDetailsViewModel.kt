package com.example.t_project.ui.jokeDetails

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.domain.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.usecases.generationRepository.GetLocalJokeItemUseCase
import com.example.t_project.domain.usecases.generationRepository.GetLocalJokesUseCase
import com.example.t_project.domain.usecases.generationRepository.GetRemoteJokesUseCase

class JokeDetailsViewModel(
    private val jokesRepository: JokesRepository
): ViewModel() {
    private val getLocalJokeItemUseCase by lazy { GetLocalJokeItemUseCase(jokesRepository = jokesRepository) }
    private val getLocalJokesUseCase by lazy {GetLocalJokesUseCase(jokesRepository = jokesRepository)}
    private val getRemoteJokesUseCase by lazy { GetRemoteJokesUseCase(jokesRepository = jokesRepository) }
    suspend fun getJokeItem(jokeId: String, context: Context) : Joke? {
        return if (jokeId == "-1") {
            Toast.makeText(context, "Invalid joke data!", Toast.LENGTH_SHORT).show()
            null
        } else {
            getLocalJokeItemUseCase.execute(jokeId)
        }
    }

    suspend fun getBackgroundColor(jokeId: String): Int? {
        return if (getLocalJokesUseCase.execute(false).filter { it.id == jokeId }.size == 1) {
            Color.BLUE
        } else if (getRemoteJokesUseCase.execute(false).filter { it.id == jokeId }.size == 1) {
            Color.BLACK
        } else {
            null
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