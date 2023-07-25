package com.example.myofflinediary.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entries(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String,
    var content: String
)
