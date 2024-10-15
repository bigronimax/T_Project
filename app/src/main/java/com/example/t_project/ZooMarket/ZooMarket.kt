package com.example.t_project.ZooMarket

class ZooMarket {
    fun catOrDog(animal: Animal): String {
        if (animal is Cat) {
            return "Кошка"
        }
        else if (animal is Dog) {
            return "Собака"
        }
        return "Ни кошка, ни собака"
    }
}