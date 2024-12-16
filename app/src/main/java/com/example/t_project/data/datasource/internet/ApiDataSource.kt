package com.example.t_project.data.datasource.internet

import com.example.t_project.data.entity.internet.ResponseData
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