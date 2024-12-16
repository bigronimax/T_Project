package com.example.t_project.domain.repos

import com.example.t_project.data.entity.internet.ResponseData

interface JokesRemoteDataSource {
    suspend fun getJokes(amount: Int): List<ResponseData>
}