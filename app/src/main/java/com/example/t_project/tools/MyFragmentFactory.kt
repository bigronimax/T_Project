package com.example.t_project.tools

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.t_project.ui.jokeCreate.JokeCreateFragment
import com.example.t_project.ui.jokeDetails.JokeDetailsFragment
import com.example.t_project.ui.jokeList.JokeListFragment

class MyFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            JokeListFragment::class.java -> JokeListFragment()
            JokeDetailsFragment::class.java -> JokeDetailsFragment()
            JokeCreateFragment::class.java -> JokeCreateFragment()
            else -> super.instantiate(classLoader, className)
        }
}