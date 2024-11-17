package com.example.t_project.domain.reposImpl

import com.example.t_project.domain.models.Joke
import com.example.t_project.domain.repos.JokesGenerationRepository

class JokesGenerationRepositoryImpl : JokesGenerationRepository {
    private var countToGenerate: Int = 10

    private val data = mutableListOf<Joke>()
    override fun setCountToGenerate(count: Int) {
        countToGenerate = count
    }

    override fun generateJokeData(): List<Joke> {
        data.clear()
        data.addAll(buildList {
            for (i in 0 until countToGenerate ) {
                add(generateRandomJoke(i))
            }
        })
        return data
    }

    override fun getJokeData(): List<Joke> {
        return data
    }

    private fun generateRandomJoke(index: Int): Joke {
        val questionText = "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah"
        val answerText = "hah hah hah hah hah hah hah hah hah hah hah hah"
        val categoryIndex = index + 1
        return Joke(
            id = index,
            category = "category $categoryIndex",
            question = questionText,
            answer = answerText,
        )
    }
}
