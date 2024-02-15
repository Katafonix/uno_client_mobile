package com.example.uno.presentation.mainmenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.uno.R
import com.example.uno.databinding.FragmentMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    private val viewModel by viewModels<MainMenuViewModel>()

    private lateinit var binding: FragmentMainMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainMenuBinding.bind(view)


        viewModel.signIn()
        setupListeners()
    }

    private fun setupListeners() {
        binding.launchCreateLobbyButton.setOnClickListener {
            onCreateLobbyScreen()
        }

        binding.launchConnectToLobbyButton.setOnClickListener {
            onLaunchJoinLobbyScreen()
        }
    }

    private fun onCreateLobbyScreen() {
        viewModel.launchCreateLobbyScreen()
    }

    private fun onLaunchJoinLobbyScreen() {
        viewModel.launchJoinLobbyScreen()
    }

}