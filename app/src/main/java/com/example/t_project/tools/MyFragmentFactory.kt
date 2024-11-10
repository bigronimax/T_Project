package com.example.t_project.tools

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.t_project.ui.jokesList.JokeDetailsFragment
import com.example.t_project.ui.jokesList.JokesListFragment

class MyFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            JokesListFragment::class.java -> JokesListFragment()
            JokeDetailsFragment::class.java -> JokeDetailsFragment()
            else -> super.instantiate(classLoader, className)
        }
}