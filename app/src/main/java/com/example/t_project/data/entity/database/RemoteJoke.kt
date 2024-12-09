package com.example.t_project.data.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.t_project.domain.entity.Joke

@Entity(tableName = "remoteJokes")
data class RemoteJoke(
    @PrimaryKey
    val id: String,
    val category: String,
    val question: String,
    val answer: String,
    val source: Joke.SourceEnum
)
