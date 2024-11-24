package com.example.t_project.domain.usecases

import com.example.t_project.domain.repos.JokesGenerationRepository

class CheckNewJokesUseCase(private val jokesGenerationRepository: JokesGenerationRepository) {

    fun execute(): Boolean {
        return jokesGenerationRepository.checkNewJokes()
    }
}