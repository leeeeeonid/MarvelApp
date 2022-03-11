package com.lealpy.marvelapp.presentation.screens.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.databinding.ItemCharacterBinding
import com.lealpy.marvelapp.presentation.models.CharacterUi

class CharacterAdapter(
    private val onItemClick: (characterUi: CharacterUi) -> Unit,
) : ListAdapter<CharacterUi, CharacterAdapter.CharacterHolder>(DiffCallback()) {

    inner class CharacterHolder(
        private val binding: ItemCharacterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val requestManager = Glide.with(itemView.context)

        fun bind(characterUi: CharacterUi) {
            binding.characterItemName.text = characterUi.name
            binding.characterItemDescription.text = characterUi.description

            requestManager
                .load(characterUi.imageURL)
                .placeholder(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                .error(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                .into(binding.characterItemImage)

            binding.root.setOnClickListener {
                onItemClick(characterUi)
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

    class DiffCallback : DiffUtil.ItemCallback<CharacterUi>() {
        override fun areItemsTheSame(oldItem: CharacterUi, newItem: CharacterUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterUi, newItem: CharacterUi): Boolean {
            return oldItem == newItem
        }
    }

}
