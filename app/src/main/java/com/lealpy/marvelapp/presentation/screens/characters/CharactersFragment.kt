package com.lealpy.marvelapp.presentation.screens.characters

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.FragmentCharactersBinding
import com.lealpy.marvelapp.presentation.utils.Const.CHARACTER_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter { character ->
        val args = bundleOf(CHARACTER_KEY to character)
        findNavController().navigate(
            R.id.action_charactersFragment_to_filtersFragment
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersBinding.bind(view)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.recyclerView.adapter = characterAdapter

        val characterDecoration = CharacterDecoration(
            requireContext().resources.getDimension(R.dimen.dimen_8_dp).toInt()
        )

        binding.recyclerView.addItemDecoration(characterDecoration)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onSwipedRefresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.filtersBtn.setOnClickListener {
            findNavController().navigate(R.id.action_charactersFragment_to_filtersFragment)
        }
    }

    private fun initObservers() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            characterAdapter.submitList(characters)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }
    }

}