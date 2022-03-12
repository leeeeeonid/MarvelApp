package com.lealpy.marvelapp.presentation.screens.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        initObservers()
    }

    private fun initObservers() {
        viewModel.character.observe(viewLifecycleOwner) { characterUi ->
            binding.characterName.text = characterUi.name
            binding.characterDescription.text = characterUi.description

            Glide.with(requireContext())
                .load(characterUi.imageURL)
                .placeholder(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                .error(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                .into(binding.characterImage)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }
    }

}
