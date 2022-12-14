package com.codialstudent.noteapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.noteapp.adapters.AdapterAddToDo
import com.codialstudent.noteapp.adapters.AddToDoDAta
import com.codialstudent.noteapp.databinding.ActivityAddToDoBinding
import java.util.*

class AddToDo : AppCompatActivity() {
    private lateinit var binding: ActivityAddToDoBinding
    private lateinit var adapter: AdapterAddToDo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        MySharedPreference.init(this)
        val degreeList = arrayListOf("", "Urgent", "High", "Normal", "Low")
        val degreListAdap = arrayListOf("Urgent", "High", "Normal", "Low")
        adapter = AdapterAddToDo(this, degreListAdap)
        binding.edtDegree.adapter = adapter
        var degree = ""
        var tvCreateDate = ""
        var tvDeadline = ""
        binding.edtDegree.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                degree = degreeList[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@AddToDo, "Please choose one of them", Toast.LENGTH_SHORT).show()
            }

        }


        binding.btnSave.setOnClickListener {
            val edtName = binding.edtName.text.toString()
            val edtDescription = binding.edtDescription.text.toString()

            if (degree.isNotEmpty() && edtName.isNotEmpty() && edtDescription.isNotEmpty() && tvCreateDate.isNotEmpty() && tvDeadline.isNotEmpty()) {
                val list = MySharedPreference.list
                list["Open"]?.add(
                    AddToDoDAta(
                        edtName,
                        edtDescription,
                        degree,
                        tvCreateDate,
                        tvDeadline
                    )
                )
                MySharedPreference.list = list
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
                finish()
                binding.edtName.text.clear()
                binding.edtDescription.text.clear()
                binding.tvDeadline.text = ""
                binding.tvCreateDate.text = ""
                binding.tvCreateDate.hint = "To do create date"
                binding.tvDeadline.hint = "To do deadline"
            } else {
                Toast.makeText(this, "Please fill out all forms", Toast.LENGTH_SHORT).show()
            }

        }


        binding.tvCreateDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    //lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)

                    tvCreateDate = "$dayOfMonth.$monthOfYear.$year"
                    binding.tvCreateDate.text = tvCreateDate
                },
                year,
                month,
                day
            )

            dpd.show()
        }
        binding.tvDeadline.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    //lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)

                    tvDeadline = "$dayOfMonth.${monthOfYear+1}.$year"
                    binding.tvDeadline.text = tvDeadline

                },
                year,
                month,
                day
            )

            dpd.show()
        }

    }
}