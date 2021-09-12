package com.example.booklibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.booklibrary.adapter.CustomAdapter
import com.example.booklibrary.data.DataBaseHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll){
            val dbHelper = DataBaseHelper(this)
            dbHelper.deleteAllData()
            Toast.makeText(this, "All data was deleted.", Toast.LENGTH_LONG).show()
        }
        recreate()
        return super.onOptionsItemSelected(item)
    }
}