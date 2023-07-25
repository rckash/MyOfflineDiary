package com.example.myofflinediary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myofflinediary.databinding.ActivityMainBinding
import com.example.myofflinediary.recyclerview.EntryAdapter
import com.example.myofflinediary.roomdatabase.Entries
import com.example.myofflinediary.roomdatabase.EntriesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDB: EntriesDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EntryAdapter
    private lateinit var entryList: MutableList<Entries>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database instantiation
        appDB = EntriesDatabase.invoke(this)

        //recyclerview setup
        recyclerView = binding.rvDiary
        recyclerView.layoutManager = LinearLayoutManager(this)

        entryList = viewEntries()

        //adapter setup
        adapter = EntryAdapter(entryList)
        recyclerView.adapter = adapter


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

    private fun viewEntries(): MutableList<Entries> {
        lateinit var entries: MutableList<Entries>
        val newEntries = mutableListOf<Entries>()

        GlobalScope.launch(Dispatchers.IO) {
            for (entry in appDB.getEntries().getAllEntries()) {
                newEntries.add(entry)
            }
            withContext(Dispatchers.Main) {
                adapter.entries = newEntries
                adapter.notifyDataSetChanged()
            }
        }
        return newEntries
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