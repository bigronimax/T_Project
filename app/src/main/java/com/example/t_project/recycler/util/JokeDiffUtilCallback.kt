package com.example.t_project.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.example.t_project.data.Joke

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
            oldItem.questionCard != newItem.questionCard -> JokeQuestionPayload(newItem.questionCard)
            oldItem.answerCard != newItem.answerCard -> JokeAnswerPayload(newItem.answerCard)
            else -> null
        }
    }
    data class JokeCategoryPayload(val category: String)
    data class JokeQuestionPayload(val questionCard: String)
    data class JokeAnswerPayload(val answerCard: String)
}