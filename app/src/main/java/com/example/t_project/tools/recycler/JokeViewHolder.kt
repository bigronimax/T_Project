package com.example.t_project.tools.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.t_project.domain.models.Joke
import com.example.t_project.databinding.JokeItemBinding

class JokeViewHolder(private val binding: JokeItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        bindCategory(joke.category)
        bindQuestion(joke.question)
        bindAnswer(joke.answer)
        bindBackgroundColor(joke.source.color)
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
    fun bindBackgroundColor(color: Int) {
        binding.card.setCardBackgroundColor(color)
    }
}