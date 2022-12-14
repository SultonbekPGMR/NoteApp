package com.codialstudent.noteapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.codialstudent.noteapp.R
import com.codialstudent.noteapp.databinding.AddToDoItemBinding

class AdapterAddToDo(val context: Context, val list: ArrayList<String>) :
    BaseAdapter() {
    override fun getCount(): Int = list.size+1

    override fun getItem(p0: Int): Any = list[p0-1]

    override fun getItemId(p0: Int): Long = p0-1.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val images = ArrayList<Int>()
        images.add(R.drawable.flag_1)
        images.add(R.drawable.flag__2_)
        images.add(R.drawable.flag__3_)
        images.add(R.drawable.flag__4_)


        val addToDoBinding = AddToDoItemBinding.inflate(LayoutInflater.from(context), p2, false)
        if (p0 != 0) {
            addToDoBinding.hintTv.hint =""
            addToDoBinding.name.text = list[p0-1]
            addToDoBinding.image.setImageResource(images[p0-1])
        }

        return addToDoBinding.root
    }
}