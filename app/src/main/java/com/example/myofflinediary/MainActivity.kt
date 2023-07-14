package com.example.myofflinediary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myofflinediary.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDB: EntriesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database instantiation
        appDB = EntriesDatabase.invoke(this)

        //create
        binding.btnSave.setOnClickListener {
            var title = binding.etTitleMain.text.toString()
            var content = binding.etContentMain.text.toString()
            var entry = Entries(0, title, content)
            saveEntry(entry)
        }

        //read
        binding.btnView.setOnClickListener {
            viewEntries()
        }

        //update
        binding.btnUpdate.setOnClickListener {
            var title = binding.etTitleMain.text.toString()
            var content = binding.etContentMain.text.toString()
            var entry = Entries(0, title, content)
            updateEntry(entry)
        }

        //delete
        binding.btnDelete.setOnClickListener {
            var title = binding.etTitleMain.text.toString()
            var content = binding.etContentMain.text.toString()
            var entry = Entries(0, title, content)
            saveEntry(entry)
        }
    }

    private fun saveEntry(entries: Entries) {
        GlobalScope.launch(Dispatchers.IO) {
            appDB.getEntries().addEntry(entries)
        }
        Toast.makeText(applicationContext, "Entry Saved", Toast.LENGTH_SHORT).show()
    }

    private fun viewEntries() {
        lateinit var entries: List<Entries>
        GlobalScope.launch(Dispatchers.IO) {
            entries = appDB.getEntries().getAllEntries()

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, entries.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateEntry(entries: Entries) {
        GlobalScope.launch(Dispatchers.IO) {
            appDB.getEntries().updateEntry(entries)
        }
        Toast.makeText(applicationContext, "Entry Updated", Toast.LENGTH_SHORT).show()
    }

    private fun deleteEntry(entries: Entries) {
        GlobalScope.launch(Dispatchers.IO) {
            appDB.getEntries().deleteEntry(entries)
        }
        Toast.makeText(applicationContext, "Entry Deleted", Toast.LENGTH_SHORT).show()
    }
}