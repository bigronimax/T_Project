package com.example.t_project.ZooMarket

fun main() {
    val zooMarket = ZooMarket()
    val Bobik = Husky(7, 12.5f)
    val Barsik = ScottishCat(4, 8.5f)

    println(zooMarket.catOrDog(Bobik))
    println(zooMarket.catOrDog(Barsik))
}