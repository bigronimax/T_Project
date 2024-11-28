package com.example.t_project.domain.models

import com.example.t_project.domain.models.response.ResponseData

data class Joke(
    val id: String = "",
    val category: String,
    val question: String,
    val answer: String,
    val source: SourceEnum
) {

    enum class SourceEnum { LOCAL, REMOTE }
    companion object{

        fun fromResponse(responseData: ResponseData) = Joke(
            id = responseData.id,
            category = responseData.category,
            question = responseData.setup,
            answer = responseData.delivery,
            source = SourceEnum.REMOTE
        )
    }
}
