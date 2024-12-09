package com.example.t_project.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.t_project.domain.entity.Joke
import com.example.t_project.databinding.JokeItemBinding

class JokeViewHolder(private val binding: JokeItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        bindCategory(joke.category)
        bindQuestion(joke.question)
        bindAnswer(joke.answer)
        when {
            joke.source == Joke.SourceEnum.CACHE -> bindBackgroundColor(CACHE_COLOR)
            joke.source == Joke.SourceEnum.LOCAL -> bindBackgroundColor(LOCAL_COLOR)
            joke.source == Joke.SourceEnum.REMOTE -> bindBackgroundColor(REMOTE_COLOR)
        }
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