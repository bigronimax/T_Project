package com.example.t_project.ui.jokeCreate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.t_project.R
import com.example.t_project.databinding.FragmentJokeCreateBinding
import com.example.t_project.domain.models.Joke
import com.example.t_project.ui.jokeList.JokeListFragment
import kotlinx.coroutines.launch

class JokeCreateFragment : Fragment(R.layout.fragment_joke_create) {

    private val binding: FragmentJokeCreateBinding by viewBinding(FragmentJokeCreateBinding::bind)

    private lateinit var viewModel: JokeCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            JokeCreateViewModel.provideFactory()
        )[JokeCreateViewModel::class.java]

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.save.setOnClickListener {
            lifecycleScope.launch {
                viewModel.addNewJoke(
                    Joke(
                        id = viewModel.getJokesSize(),
                        question = binding.question.text.toString(),
                        answer = binding.answer.text.toString(),
                        category = binding.category.text.toString(),

                        )
                )
                parentFragmentManager.popBackStack()
            }
        }

    }

    private fun handleError() {
        Toast.makeText(activity, "Invalid id data!", Toast.LENGTH_SHORT).show()
        requireActivity().finish()
    }

}