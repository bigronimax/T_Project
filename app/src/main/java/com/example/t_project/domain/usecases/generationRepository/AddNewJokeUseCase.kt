package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class AddNewJokeUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute(joke: Joke) {
        return jokesRepository.addNewJoke(joke)
    }
}