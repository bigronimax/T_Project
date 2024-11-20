package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class GetLocalJokeItemUseCase(private val jokesRepository: JokesRepository) {
    suspend fun execute(jokeId: String) : Joke? {
        return jokesRepository.getJokesMap()[jokeId]
    }
}