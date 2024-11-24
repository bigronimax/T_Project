package com.example.t_project.domain.usecases

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository

class SetCountToGenerateUseCase(private val jokesGenerationRepository: JokesGenerationRepository) {

    fun execute(count: Int){
        return jokesGenerationRepository.setCountToGenerate(count)
    }
}