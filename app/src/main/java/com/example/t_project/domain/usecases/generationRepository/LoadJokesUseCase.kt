package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class LoadJokesUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute(remoteLoad: Boolean, pageSize: Int = 10): List<Joke> {
        return jokesRepository.loadJokes(remoteLoad, pageSize)
    }

}