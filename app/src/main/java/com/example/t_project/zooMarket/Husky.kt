package com.example.t_project.zooMarket

class Husky(override var age: Int, override var weight: Float) : Dog {
    override val biteType: BiteType = BiteType.STRAIGHT
}