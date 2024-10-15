package com.example.t_project.ZooMarket

interface Dog {
    val bite: BiteType
}

enum class BiteType {
    STRAIGHT,
    OVERBITE,
    UNDERBITE,
}