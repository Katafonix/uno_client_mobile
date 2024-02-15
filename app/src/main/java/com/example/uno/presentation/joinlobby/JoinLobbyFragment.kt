package com.example.uno.presentation.joinlobby

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.uno.R
import com.example.uno.databinding.FragmentJoinLobbyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JoinLobbyFragment : Fragment(R.layout.fragment_join_lobby) {

    private val viewModel by viewModels<JoinLobbyViewModel>()
    private lateinit var binding: FragmentJoinLobbyBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentJoinLobbyBinding.bind(view)

        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.stateLiveValue.collect { state ->
                binding.lobbyCodeTextInput.error = state.codeError
                binding.progressBar.isVisible = state.showProgressBar
                binding.joinLobbyButton.isEnabled = state.enableButtons
            }
        }
    }

    private fun setupListeners() {
        binding.joinLobbyButton.setOnClickListener { onJoinPressed() }
        binding.lobbyCodeEditText.addTextChangedListener {
            viewModel.cleanWordError()
        }
    }

    private fun onJoinPressed() {
        val code = binding.lobbyCodeEditText.text.toString()
        viewModel.joinLobby(code)
    }

}