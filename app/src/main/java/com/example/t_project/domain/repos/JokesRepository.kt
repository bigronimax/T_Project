package com.example.t_project.domain.repos

import com.example.t_project.domain.models.Joke

interface JokesRepository {
    suspend fun loadJokes(remoteLoad: Boolean, pageSize: Int): List<Joke>
    suspend fun getJokeItem(jokeId: String): Joke?
    suspend fun addNewJoke(question: String, answer: String, category: String, source: Joke.SourceEnum)

}