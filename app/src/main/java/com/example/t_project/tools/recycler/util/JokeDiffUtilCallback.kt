package com.example.t_project.tools.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.example.t_project.domain.models.Joke

class JokeDiffUtilCallback(
    private val oldList: List<Joke>,
    private val newList: List<Joke>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when {
            oldItem.category != newItem.category -> JokeCategoryPayload(newItem.category)
            oldItem.question != newItem.question -> JokeQuestionPayload(newItem.question)
            oldItem.answer != newItem.answer -> JokeAnswerPayload(newItem.answer)
            else -> null
        }
    }
    data class JokeCategoryPayload(val category: String)
    data class JokeQuestionPayload(val question: String)
    data class JokeAnswerPayload(val answer: String)
}