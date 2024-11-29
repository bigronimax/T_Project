package com.example.t_project.domain.models

import android.graphics.Color

data class Joke(
    val id: String = "",
    val category: String,
    val question: String,
    val answer: String,
    val source: SourceEnum
) {
    enum class SourceEnum(val color: Int) { LOCAL(Color.BLUE), REMOTE(Color.BLACK), CACHE(Color.RED) }

}
