package com.example.t_project.ZooMarket

class SiameseCat(override var age: Int, override var weight: Float) : Animal(), Cat {
    override val behavior: BehaviorType = BehaviorType.PASSIVE
}