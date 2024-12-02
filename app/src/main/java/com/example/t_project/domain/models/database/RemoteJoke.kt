package com.example.t_project.domain.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.t_project.domain.models.Joke

@Entity(tableName = "remoteJokes")
data class RemoteJoke(
    @PrimaryKey
    val id: String = "",
    val category: String,
    val question: String,
    val answer: String,
    val source: Joke.SourceEnum
)
