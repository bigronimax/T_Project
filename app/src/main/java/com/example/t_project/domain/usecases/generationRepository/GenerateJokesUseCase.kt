package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class GenerateJokesUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute() : List<Joke> {
        return jokesRepository.generateJokeData()
    }
}