package com.example.t_project.domain.repos

import com.example.t_project.domain.models.Joke

interface JokesGenerationRepository {

    fun setCountToGenerate(count: Int)
    suspend fun generateJokeData() : List<Joke>
    suspend fun getJokeData(): List<Joke>
    suspend fun setNewJoke(joke: Joke)
}