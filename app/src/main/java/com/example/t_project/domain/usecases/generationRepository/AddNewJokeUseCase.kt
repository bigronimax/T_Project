package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository

class AddNewJokeUseCase(private val jokesRepository: JokesRepository) {

    suspend fun execute(question: String, answer: String, category: String) {
        return jokesRepository.addNewJoke(question, answer, category)
    }
}