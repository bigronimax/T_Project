package com.example.t_project.zooMarket

class SiameseCat(override var age: Int, override var weight: Float) : Cat {
    override val behaviorType: BehaviorType = BehaviorType.PASSIVE
}