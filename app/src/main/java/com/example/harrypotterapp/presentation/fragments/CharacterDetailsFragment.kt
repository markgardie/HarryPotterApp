package com.example.harrypotterapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.FragmentCharacterDetailsBinding
import com.example.harrypotterapp.domain.models.CharacterEntity


class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding: FragmentCharacterDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterDetailsBinding is null")

    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCharacterDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val character = args.character
        with(binding) {
            ivDetailsPhoto.load(character.image)
            tvDetailsName.text = character.name


            tvDetailsGender.text = String.format(
                getString(R.string.gender),
                character.gender
            )

            tvDetailsSpecies.text = String.format(
                getString(R.string.species),
                character.species
            )

            tvDetailsHouse.text = String.format(
                getString(R.string.house),
                character.house
            )

            tvDetailsAncestry.text = String.format(
                getString(R.string.ancestry),
                character.ancestry
            )

            tvDetailsEyeColour.text = String.format(
                getString(R.string.eye_color),
                character.eyeColour
            )

            tvDetailsHairColour.text = String.format(
                getString(R.string.hair_colour),
                character.hairColour
            )

            tvDetailsPatronus.text = String.format(
                getString(R.string.patronus),
                character.patronus
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}