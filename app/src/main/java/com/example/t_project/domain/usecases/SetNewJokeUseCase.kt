package com.example.t_project.domain.usecases

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository


class SetNewJokeUseCase(private val jokesGenerationRepository: JokesGenerationRepository) {

    suspend fun execute(joke: Joke) {
        return jokesGenerationRepository.setNewJoke(joke)
    }
}