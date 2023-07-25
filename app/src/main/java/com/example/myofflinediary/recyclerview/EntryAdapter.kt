package com.example.myofflinediary.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myofflinediary.databinding.EntryItemLayoutBinding
import com.example.myofflinediary.roomdatabase.Entries

class EntryAdapter (var entries: List<Entries>): RecyclerView.Adapter<EntryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EntryItemLayoutBinding.inflate(inflater, parent, false)
        return EntryItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: EntryItemViewHolder, position: Int) {
        holder.bind(entries[position])
    }

}