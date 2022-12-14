package com.codialstudent.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.noteapp.adapters.ToDoListAdapter
import com.codialstudent.noteapp.databinding.ActivityToDoListBinding

class ToDoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToDoListBinding
    private lateinit var adapter: ToDoListAdapter
    private lateinit var titleList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        MySharedPreference.init(this)
        titleList = arrayListOf("Open", "Development", "Uploading", "Reject", "Closed")
        MySharedPreference.init(this)
        adapter = ToDoListAdapter(this, MySharedPreference.list, titleList)
        binding.myExpandableListView.setAdapter(adapter)


        binding.myExpandableListView.setOnChildClickListener { _, view, i, i2, l ->

            val infoList = MySharedPreference.list[titleList[i]]!![i2]
            val intent = Intent(this, ShowInfoActivity::class.java)
            intent.putExtra("keyInfo", infoList)
            intent.putExtra("keyTitle", titleList[i])
            startActivity(intent)
            true
        }
    }

    override fun onResume() {
        adapter = ToDoListAdapter(this, MySharedPreference.list, titleList)
        binding.myExpandableListView.setAdapter(adapter)
        super.onResume()
    }
}
