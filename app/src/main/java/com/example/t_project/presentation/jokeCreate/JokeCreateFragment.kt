package com.example.t_project.presentation.jokeCreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.t_project.R
import com.example.t_project.databinding.FragmentJokeCreateBinding
import com.example.t_project.domain.entity.Joke

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
            viewModel.addNewJoke(
                question = binding.question.text.toString(),
                answer = binding.answer.text.toString(),
                category = binding.category.text.toString(),
                source = Joke.SourceEnum.LOCAL,
            )
            parentFragmentManager.popBackStack()
        }

    }

}