package com.lealpy.marvelapp.presentation.screens.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.FragmentDetailsBinding
import com.lealpy.marvelapp.presentation.screens.characters.CharacterDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        initViews()
        initObservers()
    }

    private fun initViews() {

    }

    private fun initObservers() {

    }

}