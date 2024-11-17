package com.example.t_project.domain.usecases

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository

class GenerateJokesUseCase(private val jokesGenerationRepository: JokesGenerationRepository) {

    suspend fun execute() : List<Joke> {
        return jokesGenerationRepository.generateJokeData()
    }
}