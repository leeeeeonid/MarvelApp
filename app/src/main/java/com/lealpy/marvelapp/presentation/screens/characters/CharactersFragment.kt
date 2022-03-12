package com.lealpy.marvelapp.presentation.screens.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.FragmentCharactersBinding
import com.lealpy.marvelapp.domain.models.SortBy
import com.lealpy.marvelapp.presentation.utils.Const.APP_LOG_TAG
import com.lealpy.marvelapp.presentation.utils.Const.CHARACTER_ID_KEY
import com.lealpy.marvelapp.presentation.utils.Const.FILTERS_RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private lateinit var binding: FragmentCharactersBinding

    private val viewModel: CharactersViewModel by viewModels()

    private val characterAdapter = CharacterAdapter { character ->
        val args = bundleOf(CHARACTER_ID_KEY to character.id)
        findNavController().navigate(
            R.id.action_charactersFragment_to_detailsFragment,
            args,
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
            requireContext().resources.getDimension(R.dimen.dimen_36_dp).toInt()
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
            binding.recyclerView.smoothScrollToPosition(0)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }

        try {
            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.getLiveData<SortBy>(FILTERS_RESULT_KEY)
                ?.observe(viewLifecycleOwner) { sortBy ->
                    if (sortBy != null) {
                        viewModel.onSortByClicked(sortBy)
                    }
                }
        } catch (e: Exception) {
            Log.e(APP_LOG_TAG, e.message.toString())
        }

    }

}
