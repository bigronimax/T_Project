package com.example.t_project.domain.usecases

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository

class GetJokesUseCase(private val jokesGenerationRepository: JokesGenerationRepository) {

    fun execute(): List<Joke> {
        return jokesGenerationRepository.getJokeData()
    }

}