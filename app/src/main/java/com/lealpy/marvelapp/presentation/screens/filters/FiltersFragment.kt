package com.lealpy.marvelapp.presentation.screens.filters

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.FragmentFiltersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersFragment : Fragment(R.layout.fragment_filters) {

    private lateinit var binding: FragmentFiltersBinding
    private val viewModel: FiltersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFiltersBinding.bind(view)
        initViews()
        initObservers()
        initToolbar()
    }

    private fun initViews() {

    }

    private fun initObservers() {

    }

    private fun initToolbar() {
        setHasOptionsMenu(true)

        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
        }
        return true
    }

}