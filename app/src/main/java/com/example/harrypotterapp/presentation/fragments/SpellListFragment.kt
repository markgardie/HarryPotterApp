package com.example.harrypotterapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.FragmentSpellListBinding
import com.example.harrypotterapp.presentation.adapters.SpellAdapter
import com.example.harrypotterapp.presentation.viewmodels.SpellState
import com.example.harrypotterapp.presentation.viewmodels.SpellViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpellListFragment : Fragment() {

    private var _binding: FragmentSpellListBinding? = null
    private val binding: FragmentSpellListBinding
        get() = _binding ?: throw RuntimeException("FragmentSpellListBinding is null")

    private val viewModel: SpellViewModel by viewModels()

    private val adapter by lazy {
        SpellAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSpellListBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvSpell.adapter = adapter

    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is SpellState.Loading -> binding.rvSpell.visibility = View.GONE
                is SpellState.Success -> {
                    binding.rvSpell.visibility = View.VISIBLE
                    adapter.submitList(it.data)
                }
                is SpellState.Error -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}