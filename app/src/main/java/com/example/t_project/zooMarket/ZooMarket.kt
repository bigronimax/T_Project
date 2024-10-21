package com.example.t_project.zooMarket

class ZooMarket {
    fun getAnimalType(animal: Animal): String {
        if (animal is Cat) {
            return "Кошка"
        }
        else if (animal is Dog) {
            return "Собака"
        }
        return "Ни кошка, ни собака"
    }
}