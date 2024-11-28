package com.example.t_project.ui.jokeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.t_project.R
import com.example.t_project.databinding.FragmentJokeListBinding
import com.example.t_project.tools.recycler.JokeAdapter
import com.example.t_project.ui.jokeCreate.JokeCreateFragment
import com.example.t_project.ui.jokeDetails.JokeDetailsFragment


class JokeListFragment : Fragment(R.layout.fragment_joke_list) {

    companion object {
        const val JOKE_ITEM_ID_KEY = "itemIdKey"
    }

    private val binding: FragmentJokeListBinding by viewBinding(FragmentJokeListBinding::bind)

    private lateinit var viewModel: JokeListViewModel

    private var isLoading = false;

    private val adapter = JokeAdapter {

        val fragment = JokeDetailsFragment()

        val args = Bundle()
        args.putString(JOKE_ITEM_ID_KEY, it)
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
        viewModel = ViewModelProvider(this, JokeListViewModel.provideFactory())
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

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    viewModel.loadJokes()
                }
            }
        })

    }
    private fun loadData() {

        viewModel.loadJokes(false)

        viewModel.jokesLiveData.observe(viewLifecycleOwner) { jokes ->
            binding.empty.visibility =
                if (jokes.isEmpty()) View.VISIBLE else View.GONE
            binding.recyclerView.visibility =
                if (jokes.isEmpty()) View.GONE else View.VISIBLE

            adapter.setNewData(jokes)
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner){ loading ->
            isLoading = loading
            binding.progress.visibility =
                if (isLoading) ProgressBar.VISIBLE else ProgressBar.GONE
        }

    }
    private fun createButtonClickListener() {

        binding.create.setOnClickListener {

            val fragment = JokeCreateFragment()

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

}