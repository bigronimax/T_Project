package com.example.t_project.zooMarket

class ScottishCat(override var age: Int, override var weight: Float) : Cat {
    override val behaviorType: BehaviorType = BehaviorType.ACTIVE
}