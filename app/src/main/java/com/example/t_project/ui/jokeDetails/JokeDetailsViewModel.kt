package com.example.t_project.ui.jokeDetails

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import com.example.t_project.domain.reposImpl.JokesGenerationRepositoryImpl
import com.example.t_project.domain.usecases.GetJokesUseCase

class JokeDetailsViewModel(
    private val generationRepository: JokesGenerationRepository
): ViewModel() {
    private val getJokesUseCase by lazy { GetJokesUseCase(jokesGenerationRepository = generationRepository) }

    suspend fun getJokeItem(jokeId: Int, context: Context) : Joke? {
        return if (jokeId == -1) {
            Toast.makeText(context, "Invalid joke data!", Toast.LENGTH_SHORT).show()
            null
        } else {
            getJokesUseCase.execute(false)[jokeId]
        }
    }


    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeDetailsViewModel(
                    generationRepository = JokesGenerationRepositoryImpl,
                )
            }
        }
    }

}