package com.example.t_project.domain.repos

import com.example.t_project.domain.models.Joke

interface JokesRepository {

    fun setCountToGenerate(count: Int)
    suspend fun generateJokeData() : List<Joke>
    suspend fun getLocalJokes(delay: Boolean): List<Joke>
    suspend fun getRemoteJokes(delay: Boolean): List<Joke>
    suspend fun getJokesMap() : Map<String, Joke>
    suspend fun addNewJoke(question: String, answer: String, category: String)
    suspend fun loadRemoteJokes(pageSize: Int)

}