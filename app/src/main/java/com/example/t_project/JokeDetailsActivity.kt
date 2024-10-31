package com.example.t_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t_project.data.Joke
import com.example.t_project.data.JokeGenerator
import com.example.t_project.databinding.ActivityJokeDetailsBinding

class JokeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeDetailsBinding

    private val generator = JokeGenerator

    private var jokePosition: Int = -1
    companion object {

        private const val JOKE_POSITION_EXTRA = "JOKE_POSITION"
        fun getInstance(context: Context, jokePosition: Int): Intent {
            return Intent(context, JokeDetailsActivity::class.java).apply {
                putExtra(JOKE_POSITION_EXTRA, jokePosition)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleExtra()
    }

    private fun handleExtra() {
        jokePosition = intent.getIntExtra(JOKE_POSITION_EXTRA, -1)

        if (jokePosition == -1) {
            handleError()
        } else {
            val item = generator.data[jokePosition]
            setupJokeData(item)
        }
    }

    private fun setupJokeData(item: Joke) {
        with(binding) {
            question.text = item.question
            answer.text = item.answer
            category.text = item.category
        }
    }

    private fun handleError() {
        Toast.makeText(this, "Invalid joke data!", Toast.LENGTH_SHORT).show()
        finish()
    }

}