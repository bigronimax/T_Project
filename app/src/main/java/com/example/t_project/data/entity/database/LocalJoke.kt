package com.example.t_project.data.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.t_project.domain.entity.Joke

@Entity(tableName = "localJokes")
data class LocalJoke(
    @PrimaryKey
    val id: String,
    val category: String,
    val question: String,
    val answer: String,
    val source: Joke.SourceEnum
)
