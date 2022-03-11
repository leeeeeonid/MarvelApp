package com.lealpy.marvelapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.ActivityMainBinding
import com.lealpy.marvelapp.presentation.utils.CheckNetworkConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNetworkConnectionObserver()
    }

    private fun initNetworkConnectionObserver() {
        val snackbar = Snackbar.make(
            binding.root,
            getString(R.string.no_internet),
            Snackbar.LENGTH_INDEFINITE,
        )

        snackbar.show()

        CheckNetworkConnection(application).observe(this) { isConnected ->
            when (isConnected) {
                true -> snackbar.dismiss()
                false -> snackbar.show()
            }
        }
    }

}
