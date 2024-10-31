package com.example.t_project.data

class JokeGenerator {
    fun generateJokeData(): List<Joke> {
        return buildList {
            for (i in 1 .. 7) {
                add(generateRandomJoke(i))
            }
        }
    }
    private fun generateRandomJoke(index: Int): Joke {
        return Joke(
            id = index,
            category = "category $index",
            question = "question $index",
            answer = "answer $index"
        )
    }
}
