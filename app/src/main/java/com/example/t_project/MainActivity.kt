package com.example.t_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.t_project.data.JokeGenerator
import com.example.t_project.databinding.ActivityMainBinding
import com.example.t_project.recycler.JokeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = JokeAdapter()
    private val generator = JokeGenerator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecycleView()
        generateDate()
    }
    private fun createRecycleView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
    }
    private fun generateDate() {
        val data = generator.generateJokeData()
        adapter.setNewData(data)
    }
}