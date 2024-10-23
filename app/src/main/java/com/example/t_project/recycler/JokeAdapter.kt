package com.example.t_project.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.t_project.data.Joke
import com.example.t_project.databinding.JokeItemBinding
import com.example.t_project.recycler.util.JokeDiffUtilCallback

class JokeAdapter: RecyclerView.Adapter<JokeViewHolder>() {

    private var data = emptyList<Joke>()

    fun setNewData(newData: List<Joke>) {
        val diffUtilCallback = JokeDiffUtilCallback(data, newData)
        val calculatedDiff = DiffUtil.calculateDiff(diffUtilCallback)
        data = newData
        calculatedDiff.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(inflater)

        return JokeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                handleJokeClick(parent.context, adapterPosition)
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onBindViewHolder(
        holder: JokeViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
       if (payloads.isEmpty()) {
           onBindViewHolder(holder, position)
       } else {
           payloads.forEach {
               when (it) {
                   is JokeDiffUtilCallback.JokeCategoryPayload -> holder.bindCategory(it.category)
                   is JokeDiffUtilCallback.JokeQuestionPayload -> holder.bindQuestion(it.question)
                   is JokeDiffUtilCallback.JokeAnswerPayload -> holder.bindAnswer(it.answer)
               }
           }
       }
    }

    private fun handleJokeClick(context: Context, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val item = data[position]
            Toast.makeText(context, item.answer, Toast.LENGTH_SHORT).show()
        }

    }

}