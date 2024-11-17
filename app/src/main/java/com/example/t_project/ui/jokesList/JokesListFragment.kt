package com.example.t_project.ui.jokesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.t_project.R
import com.example.t_project.databinding.FragmentJokesListBinding
import com.example.t_project.tools.recycler.JokeAdapter

class JokesListFragment : Fragment(R.layout.fragment_jokes_list) {

    companion object {
        const val JOKE_ITEM_ID_KEY = "bundleKey"
    }

    private val binding: FragmentJokesListBinding by viewBinding(FragmentJokesListBinding::bind)

    private lateinit var viewModel: JokesViewModel

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
        viewModel = ViewModelProvider(this, JokesViewModel.provideFactory(requireContext()))
            .get(JokesViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecycleView()
        setData()

    }
    private fun createRecycleView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }
    private fun setData() {
        viewModel.jokesStateFlow.value?.let { adapter.setNewData(it) }
    }

}