package com.example.t_project.ZooMarket

class ScottishCat(override var age: Int, override var weight: Float) : Animal(), Cat {
    override val behavior: BehaviorType = BehaviorType.ACTIVE
}