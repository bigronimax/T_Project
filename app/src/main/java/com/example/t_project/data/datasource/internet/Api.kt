package com.example.t_project.data.datasource.internet

import com.example.t_project.data.entity.internet.JokesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("Any")
    suspend fun getJokesList(
        @Query("amount") amount: Int = 10,
        @Query("type") type: String = "twopart",
        @Query("blacklistFlags") blacklistFlags: String = listOf("nsfw", "religious", "political", "racist", "sexist", "explicit").joinToString(",")
    ): JokesResponse
}