package com.example.t_project.domain.entity

data class Joke(
    val id: String,
    val category: String,
    val question: String,
    val answer: String,
    val source: SourceEnum
) {
    enum class SourceEnum { LOCAL, REMOTE, CACHE }

}
