package com.example.t_project.domain.reposImpl

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository
import java.util.Random

object JokesGenerationRepositoryImpl : JokesGenerationRepository {
    private var countToGenerate: Int = 0

    private val data = mutableListOf<Joke>()

    private var random = Random()
    override fun setCountToGenerate(count: Int) {
        countToGenerate = count
    }

    override suspend fun generateJokeData(): List<Joke> {
        data.clear()
        data.addAll(buildList {
            for (i in 0 until countToGenerate ) {
                add(generateRandomJoke(i))
            }
        })
        return data
    }

    override suspend fun getJokeData(): List<Joke> {
        return ArrayList(data)
    }

    override suspend fun setNewJoke(joke: Joke) {
        data.add(joke)
    }

    private fun generateRandomJoke(index: Int): Joke {
        val randomInt = random.nextInt(100)
        val questionText = "blah $randomInt"
        val answerText = "hah $randomInt"
        return Joke(
            id = index,
            category = "category $randomInt",
            question = questionText,
            answer = answerText,
        )
    }
}
