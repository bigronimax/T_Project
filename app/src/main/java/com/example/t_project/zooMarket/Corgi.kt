package com.example.t_project.zooMarket

class Corgi(override var age: Int, override var weight: Float) : Dog {
    override val biteType: BiteType = BiteType.UNDERBITE
}
