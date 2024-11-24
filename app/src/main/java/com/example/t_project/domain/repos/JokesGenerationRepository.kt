package com.example.t_project.domain.repos

import com.example.t_project.domain.models.Joke

interface JokesGenerationRepository {

    fun setCountToGenerate(count: Int)
    fun checkNewJokes() : Boolean
    suspend fun generateJokeData() : List<Joke>
    suspend fun getJokeData(delay: Boolean): List<Joke>
    suspend fun addNewJoke(joke: Joke)
}