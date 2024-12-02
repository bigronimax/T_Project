package com.example.t_project.domain.internet

import com.example.t_project.domain.models.response.ResponseData
import com.example.t_project.domain.repos.JokesRemoteDataSource
import java.net.ConnectException

class ApiDataSource: JokesRemoteDataSource {
    override suspend fun getJokes(amount: Int): List<ResponseData> {
        return try {
            HttpClient.api.getJokesList(amount).jokes

        } catch (e: Throwable) {
            throw ConnectException()
        }
    }
}