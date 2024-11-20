package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class LoadRemoteJokesUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute(pageSize: Int = 10) {
        return jokesRepository.loadRemoteJokes(pageSize)
    }
}