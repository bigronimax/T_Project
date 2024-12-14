package com.example.t_project.domain.usecases.jokesRepository

import com.example.t_project.domain.entity.Joke
import com.example.t_project.domain.repos.JokesRepository
import javax.inject.Inject

class GetJokeItemUseCase (
    private val jokesRepository: JokesRepository
) {
    suspend fun execute(jokeId: String) : Joke? {
        return jokesRepository.getJokeItem(jokeId)
    }
}