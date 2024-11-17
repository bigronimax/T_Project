package com.example.t_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.t_project.databinding.ActivityMainBinding
import com.example.t_project.tools.MyFragmentFactory
import com.example.t_project.ui.jokeList.JokeListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MyFragmentFactory()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            openFragment()
    }

    private fun openFragment() {
        val fragment = JokeListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, fragment)
            .commit()
    }
}