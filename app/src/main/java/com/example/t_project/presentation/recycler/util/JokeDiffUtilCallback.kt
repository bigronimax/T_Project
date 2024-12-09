package com.example.t_project.presentation.recycler.util

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.t_project.domain.entity.Joke

class JokeDiffUtilCallback(
    private val oldList: List<Joke>,
    private val newList: List<Joke>
): DiffUtil.Callback() {

    companion object {
        const val CATEGORY_KEY = "categoryKey"
        const val QUESTION_KEY = "questionKey"
        const val ANSWER_KEY = "answerKey"
        const val SOURCE_KEY = "sourceKey"
    }
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

        val bundle = Bundle()
        when {
            oldItem.category != newItem.category -> bundle.putString(CATEGORY_KEY, newItem.category)
            oldItem.question != newItem.question -> bundle.putString(QUESTION_KEY, newItem.question)
            oldItem.answer != newItem.answer -> bundle.putString(ANSWER_KEY, newItem.answer)
            oldItem.source != newItem.source -> bundle.putSerializable(SOURCE_KEY, newItem.source)
            else -> return null
        }
        return bundle
    }
}