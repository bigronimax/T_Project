package com.example.t_project.domain.repos

import com.example.t_project.domain.models.response.Data

interface JokesRemoteDataSource {
    suspend fun getJokes(amount: Int): List<Data>
}