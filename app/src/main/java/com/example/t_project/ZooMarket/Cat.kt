package com.example.t_project.ZooMarket

interface Cat {
    val behavior: BehaviorType
}

enum class BehaviorType {
    ACTIVE,
    PASSIVE,
}