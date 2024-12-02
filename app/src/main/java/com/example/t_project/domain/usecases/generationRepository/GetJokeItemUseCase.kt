package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class GetJokeItemUseCase(private val jokesRepository: JokesRepository) {
    suspend fun execute(jokeId: String) : Joke? {
        return jokesRepository.getJokeItem(jokeId)
    }
}