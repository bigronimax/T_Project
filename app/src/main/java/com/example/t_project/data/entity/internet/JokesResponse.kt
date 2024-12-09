package com.example.t_project.data.entity.internet

data class JokesResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<ResponseData>,
)