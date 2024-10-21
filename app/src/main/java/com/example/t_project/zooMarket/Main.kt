package com.example.t_project.zooMarket

fun main() {
    val zooMarket = ZooMarket()
    val bobik = Husky(7, 12.5f)
    val barsik = ScottishCat(4, 8.5f)

    println(zooMarket.getAnimalType(bobik))
    println(zooMarket.getAnimalType(barsik))
}