package com.example.harrypotterapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.harrypotterapp.databinding.ItemCharacterBinding
import com.example.harrypotterapp.domain.models.CharacterEntity

class CharacterAdapter: ListAdapter<CharacterEntity, CharacterViewHolder>(CharacterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)

        with(holder.binding) {
            ivPhoto.load(character.image)
            tvName.text = character.name
            tvBirthday.text = character.dateOfBirth
            tvSpecies.text = character.species

        }
    }
}