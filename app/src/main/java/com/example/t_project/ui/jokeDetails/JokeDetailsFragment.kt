package com.example.t_project.ui.jokeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.t_project.R
import com.example.t_project.databinding.FragmentJokeDetailsBinding
import com.example.t_project.domain.models.Joke
import com.example.t_project.ui.jokeList.JokeListFragment.Companion.JOKE_ITEM_ID_KEY
import kotlinx.coroutines.launch

class JokeDetailsFragment : Fragment(R.layout.fragment_joke_details) {

    private val binding: FragmentJokeDetailsBinding by viewBinding(FragmentJokeDetailsBinding::bind)

    private lateinit var viewModel: JokeDetailsViewModel

    private var jokeId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            JokeDetailsViewModel.provideFactory(requireContext())
        )[JokeDetailsViewModel::class.java]

        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jokeId = arguments?.getInt(JOKE_ITEM_ID_KEY)!!
        
        handleExtra(jokeId)
    }

    private fun handleExtra(jokeId: Int) {
        if (jokeId == -1) {
            handleError()
        } else {
            lifecycleScope.launch {
                setupJokeData(viewModel.getJokeItem(jokeId))
            }
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
        Toast.makeText(activity, "Invalid joke data!", Toast.LENGTH_SHORT).show()
        requireActivity().finish()
    }
}