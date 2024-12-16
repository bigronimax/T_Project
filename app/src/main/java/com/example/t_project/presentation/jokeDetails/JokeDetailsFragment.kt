package com.example.t_project.presentation.jokeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.t_project.App
import com.example.t_project.R
import com.example.t_project.databinding.FragmentJokeDetailsBinding
import com.example.t_project.domain.entity.Joke
import com.example.t_project.presentation.ViewModelFactory
import com.example.t_project.presentation.jokeList.JokeListFragment.Companion.JOKE_ITEM_ID_KEY
import com.example.t_project.presentation.jokeList.JokeListViewModel
import com.example.t_project.presentation.recycler.CACHE_COLOR
import com.example.t_project.presentation.recycler.LOCAL_COLOR
import com.example.t_project.presentation.recycler.REMOTE_COLOR
import javax.inject.Inject

class JokeDetailsFragment : Fragment(R.layout.fragment_joke_details) {

    private val binding: FragmentJokeDetailsBinding by viewBinding(FragmentJokeDetailsBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: JokeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireContext() as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(JokeDetailsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            JokeDetailsViewModel.provideFactory()
        )[JokeDetailsViewModel::class.java]

        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jokeId = arguments?.getString(JOKE_ITEM_ID_KEY)
        setupJoke(jokeId)
    }

    private fun setupJoke(jokeId: String?) {

        viewModel.getJokeItem(jokeId, requireContext())

        viewModel.jokesLiveData.observe(viewLifecycleOwner) { joke ->
            when (joke.source) {
                Joke.SourceEnum.CACHE -> binding.root.setBackgroundColor(CACHE_COLOR)
                Joke.SourceEnum.LOCAL -> binding.root.setBackgroundColor(LOCAL_COLOR)
                Joke.SourceEnum.REMOTE -> binding.root.setBackgroundColor(REMOTE_COLOR)
            }
            setupJokeUI(joke)
        }
    }
    private fun setupJokeUI(item: Joke) {
        with(binding) {
            question.text = item.question
            answer.text = item.answer
            category.text = item.category
        }
    }

}