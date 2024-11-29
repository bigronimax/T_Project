package com.example.t_project.domain.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class JokesResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<ResponseData>,
)