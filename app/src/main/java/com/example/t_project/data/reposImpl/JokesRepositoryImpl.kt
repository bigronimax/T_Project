package com.example.t_project.data.reposImpl

import com.example.t_project.data.datasource.database.JokeDao
import com.example.t_project.domain.entity.Joke
import com.example.t_project.data.mapper.JokeMapper
import com.example.t_project.domain.repos.JokesRemoteDataSource
import com.example.t_project.domain.repos.JokesRepository
import java.util.UUID

class JokesRepositoryImpl(
    private val jokesRemoteDataSource: JokesRemoteDataSource,
    private val jokeDao: JokeDao,
    private val jokeMapper: JokeMapper
) : JokesRepository {

    init {
        // jokeDao.insertLocalAll(generateLocalJokes().map { localJoke -> jokeMapper.toLocalJokeDbEntity(localJoke) })
    }

    private val apiJokes = mutableListOf<Joke>()

    override suspend fun loadJokes(remoteLoad: Boolean, internet: Boolean, pageSize: Int): List<Joke> {
        if (internet) {

            if (remoteLoad) {
                val jokesRemoteData = jokesRemoteDataSource.getJokes(pageSize)
                val jokeItems = jokesRemoteData.map {
                    jokeMapper.fromResponse(it)
                }

                val remoteJokes = jokeItems.map { remoteJoke -> jokeMapper.toRemoteJokeDbEntity(remoteJoke) }
                jokeDao.insertRemoteAll(remoteJokes)

                apiJokes.addAll(jokeItems)
            }

            val localJokes = jokeDao.getAllLocalJokes()
                .map { localJoke -> jokeMapper.fromLocalJokeDbEntity(localJoke) }
            return (apiJokes + localJokes).sortedBy { joke -> joke.source }
        }
        else {
            val remoteJokes = jokeDao.getAllRemoteJokes()
                .map { remoteJoke -> jokeMapper.fromRemoteJokeDbEntity(remoteJoke) }
            return if (remoteJokes.isNotEmpty()) {
                val localJokes = jokeDao.getAllLocalJokes()
                    .map { localJoke -> jokeMapper.fromLocalJokeDbEntity(localJoke) }
                (apiJokes + remoteJokes + localJokes).sortedBy { joke -> joke.source }
            } else {

                val localJokes = jokeDao.getAllLocalJokes()
                    .map { localJoke -> jokeMapper.fromLocalJokeDbEntity(localJoke) }
                return (apiJokes + localJokes).sortedBy { joke -> joke.source }
            }
        }
    }

    override suspend fun getJokeItem(jokeId: String): Joke? {
        val localJoke = jokeDao.getLocalJokeById(jokeId)
        val remoteJoke = jokeDao.getRemoteJokeById(jokeId)

        return if (localJoke != null)
            jokeMapper.fromLocalJokeDbEntity(localJoke)
        else if (remoteJoke != null)
            jokeMapper.fromRemoteJokeDbEntity(remoteJoke)
        else
            null
    }

    override suspend fun addNewJoke(
        question: String,
        answer: String,
        category: String,
        source: Joke.SourceEnum
    ) {
        val joke = Joke(
            id = UUID.randomUUID().toString(),
            question = question,
            answer = answer,
            category = category,
            source = source,
        )
        jokeDao.insertLocal(jokeMapper.toLocalJokeDbEntity(joke))
    }

    private fun generateLocalJokes(): List<Joke> {
        val generatedJokes = mutableListOf<Joke>()
        for (i in 0 until 5) {
            val id = UUID.randomUUID().toString()
            generatedJokes.add(Joke(
                id = UUID.randomUUID().toString(),
                category = "category $id",
                question = "question $id",
                answer = "answer $id",
                source = Joke.SourceEnum.LOCAL
            ))
        }
        return generatedJokes
    }
}
