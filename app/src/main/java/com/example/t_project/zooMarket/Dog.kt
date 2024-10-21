package com.example.t_project.zooMarket

interface Dog : Animal {
    val biteType: BiteType
}

enum class BiteType {
    STRAIGHT,
    OVERBITE,
    UNDERBITE,
}