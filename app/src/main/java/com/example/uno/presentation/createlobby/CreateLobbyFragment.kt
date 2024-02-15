package com.example.uno.presentation.createlobby

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elveum.elementadapter.setTintColor
import com.example.uno.R
import com.example.uno.core.presentation.collectFlow
import com.example.uno.databinding.FragmentCreateLobbyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateLobbyFragment : Fragment(R.layout.fragment_create_lobby) {

    private val viewmodel by viewModels<CreateLobbyViewModel>()

    private lateinit var binding: FragmentCreateLobbyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateLobbyBinding.bind(view)

        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.createLobbyButton.setOnClickListener {
            onPressedCreateLobby()
        }

        binding.decrementImageView.setOnClickListener {
            viewmodel.decrementCountPlayers()
        }

        binding.incrementImageView.setOnClickListener {
            viewmodel.incrementCountPlayers()
        }
    }

    private fun onPressedCreateLobby() {
        viewmodel.launchLobbyScreen()
    }

    private fun setupObservers() {
        collectFlow(viewmodel.viewState) { state ->
            binding.quantityTextView.text = state.countPlayers.toString()
            binding.decrementImageView.setEnabledAndTint(state.canDecrement)
            binding.incrementImageView.setEnabledAndTint(state.canIncrement)
        }
    }

    private fun ImageView.setEnabledAndTint(isEnabled: Boolean) {
        this.isEnabled = isEnabled
        if (isEnabled) {
            this.setTintColor(R.color.action)
        } else {
            this.setTintColor(R.color.action_disabled)
        }
    }
}