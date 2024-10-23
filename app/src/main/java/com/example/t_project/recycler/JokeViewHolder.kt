package com.example.t_project.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.t_project.data.Joke
import com.example.t_project.databinding.JokeItemBinding

class JokeViewHolder(val binding: JokeItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        bindCategory(joke.category)
        bindQuestion(joke.question)
        bindAnswer(joke.answer)
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