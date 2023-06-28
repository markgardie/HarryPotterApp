package com.example.harrypotterapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.harrypotterapp.domain.models.CharacterEntity
import com.example.harrypotterapp.domain.models.SpellEntity

class SpellDiffUtil: DiffUtil.ItemCallback<SpellEntity>(){

    override fun areItemsTheSame(oldItem: SpellEntity, newItem: SpellEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SpellEntity, newItem: SpellEntity): Boolean {
        return oldItem == newItem
    }
}