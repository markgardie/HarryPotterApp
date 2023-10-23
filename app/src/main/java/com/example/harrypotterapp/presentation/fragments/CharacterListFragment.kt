package com.example.harrypotterapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.harrypotterapp.databinding.FragmentCharacterListBinding
import com.example.harrypotterapp.presentation.adapters.CharacterAdapter
import com.example.harrypotterapp.presentation.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterListBinding is null")

    private val characterAdapter by lazy {
        CharacterAdapter()
    }

    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterListBinding.inflate(
            inflater,
            container,
            false
        )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observerViewModel()
    }

    private fun observerViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    with(binding) {
                        if (it.isLoading) {
                            charactersPb.visibility = View.VISIBLE
                            rvCharacter.visibility = View.GONE
                        }
                        if (it.characters.isNotEmpty()) {
                            charactersPb.visibility = View.GONE
                            rvCharacter.visibility = View.VISIBLE
                            characterAdapter.submitList(it.characters)
                        }
                        else viewModel.refresh()
                    }

                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCharacter.adapter = characterAdapter
        setupClickListener()
    }

    private fun setupClickListener() {
        characterAdapter.onItemClickListener = {
            val action = CharacterListFragmentDirections
                .actionCharacterListFragmentToCharacterDetailsFragment(it)
            findNavController().navigate(action)
        }

        characterAdapter.onFavoriteClickListener = {
            if (it.isFavorite) {
                viewModel.updateFavoriteCharacter(it.copy(isFavorite = false))
            }
            else {
                viewModel.updateFavoriteCharacter(it.copy(isFavorite = true))
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}