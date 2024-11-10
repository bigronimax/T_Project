package com.example.t_project.tools.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.t_project.domain.models.Joke
import com.example.t_project.databinding.JokeItemBinding
import com.example.t_project.tools.recycler.util.JokeDiffUtilCallback

class JokeAdapter(
    private val clickListener: (Int) -> Unit
): RecyclerView.Adapter<JokeViewHolder>() {

    private var data = emptyList<Joke>()

    fun setNewData(newData: List<Joke>) {
        val diffUtilCallback = JokeDiffUtilCallback(data, newData)
        val calculatedDiff = DiffUtil.calculateDiff(diffUtilCallback)
        data = newData
        calculatedDiff.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(inflater, parent, false)

        return JokeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    handleJokeClick(data[adapterPosition].id)
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

    private fun handleJokeClick(jokeId: Int) {
            clickListener(jokeId)
    }

}