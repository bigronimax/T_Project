package com.example.t_project.domain.reposImpl

import com.example.t_project.domain.internet.ApiDataSource
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository
import java.util.Random
import java.util.UUID

object JokesRepositoryImpl : JokesRepository {

    private val jokesRemoteDataSource = ApiDataSource()

    private val jokesDataMap = mutableMapOf<String, Joke>()

    private val jokesData = mutableListOf<Joke>()

    override suspend fun loadJokes(remoteLoad: Boolean, pageSize: Int): List<Joke> {
        if (remoteLoad) {
            val jokesRemoteData = jokesRemoteDataSource.getJokes(pageSize)
            val jokeItems = jokesRemoteData.map {
                Joke.fromResponse(it)
            }
            for (joke in jokeItems) {
                jokesDataMap[joke.id] = joke
            }
            jokesData.addAll(jokeItems)
        }
        return jokesData.sortedBy { joke -> joke.source }
    }

    override suspend fun getJokeItem(jokeId: String): Joke? {
        return jokesDataMap[jokeId]
    }

    override suspend fun addNewJoke(question: String, answer: String, category: String, source: Joke.SourceEnum) {
        val joke = Joke(
            id = UUID.randomUUID().toString(),
            question = question,
            answer = answer,
            category = category,
            source = source,
        )
        jokesData.add(joke)
        jokesDataMap[joke.id] = joke
    }
}
