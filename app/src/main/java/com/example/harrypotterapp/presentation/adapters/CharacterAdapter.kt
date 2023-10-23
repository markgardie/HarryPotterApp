package com.example.harrypotterapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.ItemCharacterBinding
import com.example.harrypotterapp.domain.models.CharacterEntity

class CharacterAdapter: ListAdapter<CharacterEntity, CharacterViewHolder>(CharacterDiffUtil()) {

    var onItemClickListener: ((CharacterEntity) -> Unit)? = null
    var onFavoriteClickListener: ((CharacterEntity) -> Unit)? = null

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
            tvSpecies.text = character.species
            tvAncestry.text = character.ancestry
            root.setOnClickListener {
                onItemClickListener?.invoke(character)
            }
            if (character.isFavorite) ivCharacterFavorite.setImageResource(R.drawable.ic_favorite)
            else ivCharacterFavorite.setImageResource(R.drawable.ic_not_favorite)

            ivCharacterFavorite.setOnClickListener {
                onFavoriteClickListener?.invoke(character)
            }
        }


    }
}