package com.example.myofflinediary

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntriesDao {
    //Create
    @Insert
    fun addEntry(entries: Entries)

    //Read
    @Query ("SELECT * FROM entries")
    fun getAllEntries(): List<Entries>

    //Update
    @Update
    fun updateEntry(entries: Entries)

    //Delete
    @Delete
    fun deleteEntry(entries: Entries)
}