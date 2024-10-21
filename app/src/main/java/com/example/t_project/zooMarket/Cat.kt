package com.example.t_project.zooMarket

interface Cat : Animal {
    val behaviorType: BehaviorType
}

enum class BehaviorType {
    ACTIVE,
    PASSIVE,
}