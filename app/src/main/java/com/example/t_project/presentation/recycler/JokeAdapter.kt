package com.example.t_project.presentation.recycler

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.t_project.databinding.JokeItemBinding
import com.example.t_project.domain.entity.Joke
import com.example.t_project.presentation.recycler.util.JokeDiffUtilCallback
import com.example.t_project.presentation.recycler.util.JokeDiffUtilCallback.Companion.ANSWER_KEY
import com.example.t_project.presentation.recycler.util.JokeDiffUtilCallback.Companion.CATEGORY_KEY
import com.example.t_project.presentation.recycler.util.JokeDiffUtilCallback.Companion.QUESTION_KEY
import com.example.t_project.presentation.recycler.util.JokeDiffUtilCallback.Companion.SOURCE_KEY

const val LOCAL_COLOR = Color.GRAY
const val REMOTE_COLOR = Color.BLACK
const val CACHE_COLOR = Color.RED

class JokeAdapter(
    private val clickListener: (String) -> Unit
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

           val bundle = payloads[0] as Bundle

           for (key in bundle.keySet()) {
               when {
                   key.equals(CATEGORY_KEY) -> bundle.getString(key)?.let { holder.bindCategory(it) }
                   key.equals(QUESTION_KEY) -> bundle.getString(key)?.let { holder.bindQuestion(it) }
                   key.equals(ANSWER_KEY) -> bundle.getString(key)?.let { holder.bindAnswer(it) }
                   key.equals(SOURCE_KEY) -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                       bundle.getSerializable(key, Joke.SourceEnum::class.java).let {
                           when {
                               it == Joke.SourceEnum.CACHE -> holder.bindBackgroundColor(CACHE_COLOR)
                               it == Joke.SourceEnum.LOCAL -> holder.bindBackgroundColor(LOCAL_COLOR)
                               it == Joke.SourceEnum.REMOTE -> holder.bindBackgroundColor(REMOTE_COLOR)
                           }
                       }
                   }
               }
           }

       }
    }

    private fun handleJokeClick(jokeId: String) {
            clickListener(jokeId)
    }

}