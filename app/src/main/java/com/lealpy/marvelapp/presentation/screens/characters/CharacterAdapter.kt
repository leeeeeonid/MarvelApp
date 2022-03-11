package com.lealpy.marvelapp.presentation.screens.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.ItemCharacterBinding
import com.lealpy.marvelapp.domain.models.Character

class CharacterAdapter(
    private val onItemClick: (character: Character) -> Unit,
) : ListAdapter<Character, CharacterAdapter.CharacterHolder>(DiffCallback()) {

    inner class CharacterHolder(
        private val binding: ItemCharacterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val requestManager = Glide.with(itemView.context)

        fun bind(character: Character) {
            binding.characterItemName.text = character.name
            binding.characterItemDescription.text = character.description

            requestManager
                .load(character.imageURL)
                .placeholder(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                .error(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                .into(binding.characterItemImage)

            binding.root.setOnClickListener {
                onItemClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

}
