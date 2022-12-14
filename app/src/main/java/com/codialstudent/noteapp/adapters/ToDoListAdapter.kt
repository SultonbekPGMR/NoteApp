package com.codialstudent.noteapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.codialstudent.noteapp.databinding.ToDoListShowBinding
import com.codialstudent.noteapp.databinding.ToDoListShowChildBinding

class ToDoListAdapter(
    val context: Context,
    val map: HashMap<String, ArrayList<AddToDoDAta>>,
    val titleList: ArrayList<String>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int = 5
    override fun getChildrenCount(p0: Int): Int = map[titleList[p0]]!!.size

    override fun getGroup(p0: Int): Any = titleList[p0]

    override fun getChild(p0: Int, p1: Int): AddToDoDAta? = map[titleList[p0]]?.get(p1)

    override fun getGroupId(p0: Int): Long = p0.toLong()
    override fun getChildId(p0: Int, p1: Int): Long = p1.toLong()

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {

        val toDoListShowBinding =
            ToDoListShowBinding.inflate(LayoutInflater.from(context), p3, false)
        toDoListShowBinding.nameParent.text = titleList[p0]
        return toDoListShowBinding.root
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {

        val toDoListShowChildBinding =
            ToDoListShowChildBinding.inflate(LayoutInflater.from(context), p4, false)
        toDoListShowChildBinding.nameChild.text = map[titleList[p0]]!![p1].name
        return toDoListShowChildBinding.root
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}