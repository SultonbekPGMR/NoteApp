package com.codialstudent.noteapp

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.noteapp.adapters.AddToDoDAta
import com.codialstudent.noteapp.databinding.ActivityShowInfoBinding

class ShowInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        MySharedPreference.init(this)
        val infoList = intent.getSerializableExtra("keyInfo") as AddToDoDAta
        val title = intent.getStringExtra("keyTitle")
        var process = ""
        binding.radioGroup.findViewWithTag<RadioButton>(title).isChecked =
            true
        supportActionBar?.title = infoList.name
        binding.description.text = infoList.description
        binding.priority.text = infoList.degree
        binding.tvCreateDate.text = infoList.date
        binding.tvDeadline.text = infoList.deadline
        when(infoList.degree){
            "Urgent"-> binding.flagImage.setImageResource(R.drawable.flag_1)
            "High"-> binding.flagImage.setImageResource(R.drawable.flag__2_)
            "Normal"-> binding.flagImage.setImageResource(R.drawable.flag__3_)
            "Low"-> binding.flagImage.setImageResource(R.drawable.flag__4_)
        }

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            process = findViewById<RadioButton>(i).tag.toString()
        }

        binding.btnSave.setOnClickListener {
            if (process.isNotEmpty()) {
                val list = MySharedPreference.list
                list[title]!!.remove(infoList)
                list[process]!!.add(infoList)
                MySharedPreference.list = list
                Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                finish()

            }
        }


    }
}