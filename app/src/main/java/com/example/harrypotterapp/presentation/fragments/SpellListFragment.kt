package com.example.harrypotterapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.FragmentSpellListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpellListFragment : Fragment() {

    private var _binding: FragmentSpellListBinding? = null
    private val binding: FragmentSpellListBinding
        get() = _binding ?: throw RuntimeException("FragmentSpellListBinding is null")


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}