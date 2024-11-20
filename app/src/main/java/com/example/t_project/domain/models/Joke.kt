package com.example.t_project.domain.models

import com.example.t_project.domain.models.response.Data

data class Joke(
    val id: String = "",
    val category: String,
    val question: String,
    val answer: String,
) {
    companion object{

        fun fromResponse(responseData: Data) = Joke(
            id = responseData.id,
            category = responseData.category,
            question = responseData.setup,
            answer = responseData.delivery,
        )
    }
}
