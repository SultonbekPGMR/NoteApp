package com.codialstudent.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.noteapp.adapters.AddToDoDAta
import com.codialstudent.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedPreference.init(this)
        if (!MySharedPreference.cacheIsActivated) {
            MySharedPreference.cacheIsActivated = true
            loadData()
        }
        bntOnClick()


    }


    private fun bntOnClick() {
        binding.btnAddToDo.setOnClickListener {
            val intent = Intent(this, AddToDo::class.java)
            startActivity(intent)
        }

        binding.btnToDoList.setOnClickListener {
            val intent = Intent(this, ToDoListActivity::class.java)
            startActivity(intent)

        }
    }

    private fun loadData() {
        val map = HashMap<String, ArrayList<AddToDoDAta>>()
        val titleList = arrayListOf("Open", "Development", "Uploading", "Reject", "Closed")

        val list = ArrayList<AddToDoDAta>()

        for (i in titleList) {
            map[i] = list
        }
        MySharedPreference.list = map
    }


}