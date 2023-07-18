package com.example.harrypotterapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.harrypotterapp.databinding.FragmentSpellListBinding
import com.example.harrypotterapp.presentation.adapters.SpellAdapter
import com.example.harrypotterapp.presentation.viewmodels.SpellViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(binding) {
                    viewModel.uiState.collect {
                        if (it.isLoading) {
                            spellsPb.visibility = View.VISIBLE
                            rvSpell.visibility = View.GONE
                        }
                        if (it.spells.isNotEmpty()) {
                            spellsPb.visibility = View.GONE
                            rvSpell.visibility = View.VISIBLE
                            adapter.submitList(it.spells)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}