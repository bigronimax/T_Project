package com.example.t_project.domain.models.response

class Data(
    val category: String,
    val type: String,
    val setup: String,
    val delivery: String,
    val flags: Flags,
    val id: String,
    val safe: Boolean,
    val lang: String
)