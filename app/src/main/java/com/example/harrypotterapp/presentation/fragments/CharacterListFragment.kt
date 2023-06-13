package com.example.harrypotterapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterListBinding is null")

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}