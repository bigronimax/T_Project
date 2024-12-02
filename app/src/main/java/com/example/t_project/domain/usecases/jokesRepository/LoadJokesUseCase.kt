package com.example.t_project.domain.usecases.jokesRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class LoadJokesUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute(remoteLoad: Boolean, internet: Boolean, pageSize: Int = 10): List<Joke> {
        return jokesRepository.loadJokes(remoteLoad, internet, pageSize)
    }

}