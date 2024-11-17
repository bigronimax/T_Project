package com.example.t_project.ui.jokeList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.t_project.R
import com.example.t_project.databinding.FragmentJokeListBinding
import com.example.t_project.tools.recycler.JokeAdapter
import com.example.t_project.ui.jokeCreate.JokeCreateFragment
import com.example.t_project.ui.jokeDetails.JokeDetailsFragment
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class JokeListFragment : Fragment(R.layout.fragment_joke_list) {

    companion object {
        const val JOKE_ITEM_ID_KEY = "bundleKey"
        const val JOKE_LAST_ID_KEY = "bundleKey"
    }

    private val binding: FragmentJokeListBinding by viewBinding(FragmentJokeListBinding::bind)

    private lateinit var viewModel: JokeListViewModel

    private val adapter = JokeAdapter {

        val fragment = JokeDetailsFragment()

        val args = Bundle()
        args.putInt(JOKE_ITEM_ID_KEY, it)
        fragment.setArguments(args)

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, JokeListViewModel.provideFactory(requireContext()))
            .get(JokeListViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecycleView()
        loadData()
        createButtonClickListener()

    }

    private fun createRecycleView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }
    private fun loadData() {

        lifecycleScope.launch {
            viewModel.loadJokes()
        }

        viewModel.mutableJokes.observe(viewLifecycleOwner) { jokes ->
            adapter.setNewData(jokes)
        }

        viewModel.mutableProgress.observe(viewLifecycleOwner){ isVisible ->
            binding.progress.visibility =
                if (isVisible) ProgressBar.VISIBLE else ProgressBar.GONE
        }

        viewModel.mutableEmpty.observe(viewLifecycleOwner) { isEmpty ->
            binding.empty.visibility =
                if (isEmpty) View.VISIBLE else View.GONE
            binding.recyclerView.visibility =
                if (isEmpty) View.GONE else View.VISIBLE
        }

    }
    private fun createButtonClickListener() {

        binding.create.setOnClickListener {

            val fragment = JokeCreateFragment()

            val args = Bundle()
//            viewModel.jokesStateFlow.value?.let { it1 -> args.putInt(JOKE_LAST_ID_KEY, it1.size) }
            viewModel.mutableJokes.value?.let {it1 -> args.putInt(JOKE_LAST_ID_KEY, it1.size) }
            fragment.setArguments(args)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

}