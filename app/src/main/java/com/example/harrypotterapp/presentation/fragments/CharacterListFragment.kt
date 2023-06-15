package com.example.harrypotterapp.presentation.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.FragmentCharacterListBinding
import com.example.harrypotterapp.presentation.adapters.CharacterAdapter
import com.example.harrypotterapp.presentation.viewmodels.CharacterState
import com.example.harrypotterapp.presentation.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint


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
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is CharacterState.Loading -> binding.rvCharacter.visibility = View.GONE
                is CharacterState.Success -> {
                    binding.rvCharacter.visibility = View.VISIBLE
                    characterAdapter.submitList(it.data)
                }
                is CharacterState.Error -> {
                    Toast.makeText(
                        requireActivity(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCharacter.adapter = characterAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}