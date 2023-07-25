package com.example.myofflinediary.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.myofflinediary.databinding.EntryItemLayoutBinding
import com.example.myofflinediary.roomdatabase.Entries

class EntryItemViewHolder (val binding: EntryItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(entry: Entries) {
        binding.tvTitleItemLayout.text = entry.title
        binding.tvContentItemLayout.text = entry.content
    }
}