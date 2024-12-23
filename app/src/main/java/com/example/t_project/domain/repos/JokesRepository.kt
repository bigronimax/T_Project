package com.example.t_project.domain.repos

import com.example.t_project.domain.entity.Joke

interface JokesRepository {
    suspend fun loadJokes(remoteLoad: Boolean, internet: Boolean, pageSize: Int): List<Joke>
    suspend fun getJokeItem(jokeId: String): Joke?
    suspend fun addNewJoke(question: String, answer: String, category: String, source: Joke.SourceEnum)

}