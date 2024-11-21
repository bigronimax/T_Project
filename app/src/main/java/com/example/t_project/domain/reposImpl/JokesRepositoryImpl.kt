package com.example.t_project.domain.reposImpl

import com.example.t_project.domain.internet.ApiDataSource
import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesRepository
import kotlinx.coroutines.delay
import java.util.Random
import java.util.UUID

object JokesRepositoryImpl : JokesRepository {

    private val jokesRemoteDataSource = ApiDataSource()

    private var countToGenerate: Int = 0

    private val jokesDataMap = mutableMapOf<String, Joke>()

    private val localJokesData = mutableListOf<Joke>()

    private val remoteJokesData = mutableListOf<Joke>()

    private var random = Random()

    override fun setCountToGenerate(count: Int) {
        countToGenerate = count
    }

    override suspend fun generateJokeData(): List<Joke> {
        localJokesData.clear()
        localJokesData.addAll(buildList {
            for (i in 0 until countToGenerate ) {
                add(generateRandomJoke())
            }
        })
        return localJokesData
    }

    override suspend fun getLocalJokes(delay: Boolean): List<Joke> {
        if (delay)
            delay(2000)
        return ArrayList(localJokesData)
    }

    override suspend fun getJokesMap(): Map<String, Joke> {
        for (joke in localJokesData) {
            jokesDataMap[joke.id] = joke
        }
        for (joke in remoteJokesData) {
            jokesDataMap[joke.id] = joke
        }
        return jokesDataMap
    }

    override suspend fun addNewJoke(question: String, answer: String, category: String) {
        val joke = Joke(
            id = UUID.randomUUID().toString(),
            question = question,
            answer = answer,
            category = category
        )
        localJokesData.add(joke)
    }

    private fun generateRandomJoke(): Joke {
        val randomInt = random.nextInt(100)
        val questionText = "blah $randomInt"
        val answerText = "hah $randomInt"
        return Joke(
            id = UUID.randomUUID().toString(),
            category = "category $randomInt",
            question = questionText,
            answer = answerText,
        )
    }

    override suspend fun getRemoteJokes(delay: Boolean): List<Joke> {
        if (delay)
            delay(2000)
        return ArrayList(remoteJokesData)
    }

    override suspend fun loadRemoteJokes(pageSize: Int) {
        val jokesData = jokesRemoteDataSource.getJokes(pageSize)
        val jokeItems = jokesData.map {
            Joke.fromResponse(it)
        }
        remoteJokesData.addAll(jokeItems)
    }
}
