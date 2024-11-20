package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class GetLocalJokesUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute(delay: Boolean): List<Joke> {
        return jokesRepository.getLocalJokes(delay)
    }

}