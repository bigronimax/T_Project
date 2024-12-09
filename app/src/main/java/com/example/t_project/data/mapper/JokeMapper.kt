package com.example.t_project.data.mapper

import com.example.t_project.domain.entity.Joke
import com.example.t_project.data.entity.database.LocalJoke
import com.example.t_project.data.entity.database.RemoteJoke
import com.example.t_project.data.entity.internet.ResponseData

class JokeMapper {

    fun fromResponse(responseData: ResponseData) = Joke(
        id = responseData.id,
        category = responseData.category,
        question = responseData.setup,
        answer = responseData.delivery,
        source = Joke.SourceEnum.REMOTE
    )
    fun toLocalJokeDbEntity(localJoke: Joke) = LocalJoke(
        id = localJoke.id,
        category = localJoke.category,
        question = localJoke.question,
        answer = localJoke.answer,
        source = localJoke.source,
    )

    fun fromLocalJokeDbEntity(localJoke: LocalJoke) = Joke(
        id = localJoke.id,
        category = localJoke.category,
        question = localJoke.question,
        answer = localJoke.answer,
        source = localJoke.source,
    )
    fun toRemoteJokeDbEntity(remoteJoke: Joke) = RemoteJoke(
        id = remoteJoke.id,
        category = remoteJoke.category,
        question = remoteJoke.question,
        answer = remoteJoke.answer,
        source = remoteJoke.source,
    )

    fun fromRemoteJokeDbEntity(remoteJoke: RemoteJoke) = Joke(
        id = remoteJoke.id,
        category = remoteJoke.category,
        question = remoteJoke.question,
        answer = remoteJoke.answer,
        source = Joke.SourceEnum.CACHE,
    )
}