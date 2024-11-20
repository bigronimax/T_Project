package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class GetRemoteJokesUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute(delay: Boolean) : List<Joke> {
        return jokesRepository.getRemoteJokes(delay)
    }
}