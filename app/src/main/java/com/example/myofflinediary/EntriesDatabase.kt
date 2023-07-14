package com.example.myofflinediary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (
    entities = [Entries::class],
    version = 1
)
abstract class EntriesDatabase: RoomDatabase() {
    abstract fun getEntries(): EntriesDao

    companion object {
        @Volatile private var instance: EntriesDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            EntriesDatabase::class.java,
            "entries"
        ).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}