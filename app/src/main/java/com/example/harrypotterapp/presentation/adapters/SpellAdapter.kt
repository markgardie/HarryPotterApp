package com.example.harrypotterapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.harrypotterapp.R
import com.example.harrypotterapp.databinding.ItemCharacterBinding
import com.example.harrypotterapp.databinding.ItemSpellBinding
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.models.SpellEntity

class SpellAdapter: ListAdapter<SpellEntity, SpellViewHolder>(SpellDiffUtil()) {

    var onItemClickListener: ((SpellEntity) -> Unit)? = null
    var onFavoriteClickListener: ((SpellEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val binding = ItemSpellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SpellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = getItem(position)

        with(holder.binding) {
            tvSpellName.text = spell.name
            tvSpellDescription.text = spell.description
            if (spell.isFavorite) ivSpellFavorite.setImageResource(R.drawable.ic_favorite)
            else ivSpellFavorite.setImageResource(R.drawable.ic_not_favorite)

            ivSpellFavorite.setOnClickListener {
                onFavoriteClickListener?.invoke(spell)
            }
        }
    }
}