package com.example.t_project.data.datasource.internet

import com.example.t_project.data.entity.internet.ResponseData
import com.example.t_project.domain.repos.JokesRemoteDataSource
import java.net.ConnectException
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val api: Api
): JokesRemoteDataSource {
    override suspend fun getJokes(amount: Int): List<ResponseData> {
        return try {
            api.getJokesList(amount).jokes

        } catch (e: Throwable) {
            throw ConnectException()
        }
    }
}