package com.example.uno.presentation.lobby

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.simpleAdapter
import com.example.uno.R
import com.example.uno.core.presentation.setupSimpleList
import com.example.uno.databinding.FragmentLobbyBinding
import com.example.uno.databinding.ItemLobbyPlayerBinding
import com.example.uno.domain.lobby.entities.LobbyPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LobbyFragment : Fragment(R.layout.fragment_lobby) {

    private val viewModel by viewModels<LobbyViewModel>()

    private lateinit var binding: FragmentLobbyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLobbyBinding.bind(view)

        val adapter = createAdapter()

        setupList(adapter)
        setupListeners()
        setupObservers()
        observeList(adapter)
    }

    private fun observeList(adapter: SimpleBindingAdapter<LobbyPlayer>) {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.playerList)
        }
    }

    private fun setupList(adapter: SimpleBindingAdapter<LobbyPlayer>) {
        binding.playerListRecyclerView.setupSimpleList()
        binding.playerListRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            binding.startGameButton.isVisible = state.showStartGameButton
            binding.readyStatusButton.text = "Не готов"
        }
    }

    private fun setupListeners() {
        binding.startGameButton.setOnClickListener {
            onStartGamePressed()
        }

        binding.readyStatusButton.setOnClickListener {
            onChangeStatusPressed()
        }
    }

    private fun onStartGamePressed() {
        TODO("Not yet implemented")
    }

    private fun onChangeStatusPressed() {
        viewModel.changeReadyStatus(1)
    }

    private fun createAdapter() = simpleAdapter<LobbyPlayer, ItemLobbyPlayerBinding> {
        areItemsSame = { oldItem, newItem -> oldItem.id == newItem.id }
        areContentsSame = { oldItem, newItem -> oldItem == newItem }

        bind { player ->
            nicknameTextView.text = player.nickname
            readyStatusTextView.text = R.string.status_is_not_ready.toString()
        }
        listeners {
            deletePlayerButton.onClick {
                viewModel.deletePlayer(it.id)
            }
        }
    }
}