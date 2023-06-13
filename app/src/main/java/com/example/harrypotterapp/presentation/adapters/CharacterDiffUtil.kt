package com.example.harrypotterapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.harrypotterapp.domain.models.CharacterEntity

class CharacterDiffUtil: DiffUtil.ItemCallback<CharacterEntity>(){

    override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem == newItem
    }
}