package com.example.t_project.domain.repos

import com.example.t_project.domain.models.Joke

interface JokesGenerationRepository {

    fun setCountToGenerate(count: Int)
    fun generateJokeData() : List<Joke>
    fun getJokeData(): List<Joke>
}