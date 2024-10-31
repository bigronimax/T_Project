package com.example.t_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t_project.data.JokeGenerator
import com.example.t_project.databinding.ActivityJokesListBinding
import com.example.t_project.recycler.JokeAdapter

class JokesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokesListBinding
    private val adapter = JokeAdapter {
        this.startActivity(JokeDetailsActivity.getInstance(this, it))
    }
    private val generator = JokeGenerator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecycleView()
        generateData()

    }
    private fun createRecycleView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun generateData() {
        val data = generator.generateJokeData()
        adapter.setNewData(data)
    }
}