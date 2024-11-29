package com.example.t_project.domain.models.response

data class JokesResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<ResponseData>,
)