package com.example.t_project.domain.internet

import com.example.t_project.domain.models.response.JokesResponse
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