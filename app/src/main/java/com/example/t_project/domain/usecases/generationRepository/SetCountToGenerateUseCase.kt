package com.example.t_project.domain.usecases.generationRepository

import com.example.t_project.domain.repos.JokesRepository

class SetCountToGenerateUseCase(private val jokesRepository: JokesRepository) {
    fun execute(count: Int){
        return jokesRepository.setCountToGenerate(count)
    }
}