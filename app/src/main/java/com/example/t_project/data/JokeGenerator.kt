package com.example.t_project.data

object JokeGenerator {

    val data = mutableListOf<Joke>()
    fun generateJokeData(): List<Joke> {
        data.clear()
        data.addAll(buildList {
            for (i in 1 .. 15) {
                add(generateRandomJoke(i))
            }
        })
        return data
    }
    private fun generateRandomJoke(index: Int): Joke {
        val questionText = "blah blah blah blah blah blah blah blah"
        val answerText = "hah hah hah hah"
        val criticalQuestionLength = 25
        val criticalAnswerLength = 10
        return Joke(
            id = index,
            category = "category $index",
            question = questionText,
            answer = answerText,
            questionCard = questionText
                .substring(0, criticalQuestionLength)
                .padEnd(criticalQuestionLength+3, '.'),
            answerCard = answerText
                .substring(0, criticalAnswerLength)
                .padEnd(criticalAnswerLength+3, '.')
        )
    }
}
