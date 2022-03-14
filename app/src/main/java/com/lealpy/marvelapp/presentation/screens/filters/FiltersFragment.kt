package com.lealpy.marvelapp.presentation.screens.filters

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.FragmentFiltersBinding
import com.lealpy.marvelapp.domain.models.SortBy
import com.lealpy.marvelapp.domain.models.SortBy.*
import com.lealpy.marvelapp.presentation.utils.Const.FILTERS_RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersFragment : Fragment(R.layout.fragment_filters) {

    private lateinit var binding: FragmentFiltersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFiltersBinding.bind(view)
        initToolbar()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.supportActionBar?.title = getString(R.string.filters_title)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filters_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
            R.id.filtersApplyBtn -> {
                val sortBy = when (binding.sortByRadioGroup.checkedRadioButtonId) {
                    R.id.sortByAlphabetBtn -> BY_ALPHABET
                    R.id.sortByDateBtn -> BY_DATE
                    R.id.sortByAlphabetDescendingBtn -> BY_ALPHABET_DESCENDING
                    R.id.sortByDateDescendingBtn -> BY_DATE_DESCENDING
                    else -> BY_ALPHABET
                }
                setFragmentResult(sortBy)
            }
        }
        return true
    }

    private fun setFragmentResult(sortBy: SortBy) {
        findNavController()
            .previousBackStackEntry
            ?.savedStateHandle
            ?.set(FILTERS_RESULT_KEY, sortBy)
        findNavController().popBackStack()
    }

}
