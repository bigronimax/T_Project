package com.example.t_project.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.t_project.data.Joke
import com.example.t_project.databinding.JokeItemBinding

class JokeViewHolder(private val binding: JokeItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        bindCategory(joke.category)
        bindQuestion(joke.questionCard)
        bindAnswer(joke.answerCard)
    }
    fun bindCategory(category: String) {
        binding.category.text = category
    }
    fun bindQuestion(question: String) {
        binding.question.text = question
    }
    fun bindAnswer(answer: String) {
        binding.answer.text = answer
    }
}