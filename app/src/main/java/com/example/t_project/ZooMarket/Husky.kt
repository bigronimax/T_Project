package com.example.t_project.ZooMarket

class Husky(override var age: Int, override var weight: Float) : Animal(), Dog {
    override val bite: BiteType = BiteType.STRAIGHT
}