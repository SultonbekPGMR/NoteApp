package com.codialstudent.noteapp

import android.content.Context
import android.content.SharedPreferences
import com.codialstudent.noteapp.adapters.AddToDoDAta
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object MySharedPreference {
    private const val NAME = "cache_final"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var cacheIsActivated: Boolean
        get() = preferences.getBoolean("state", false)
        set(value) = preferences.edit {
            if (value != null) {
                it.putBoolean("state", value)
            }
        }

     var list: HashMap<String, ArrayList<AddToDoDAta>>
        get() = gsonStringToArraylist(preferences.getString("keyList", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("keyList", arrayListToGsonString(value))
            }
        }


    private fun arrayListToGsonString(list: HashMap<String, ArrayList<AddToDoDAta>>): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    private fun gsonStringToArraylist(str: String): HashMap<String, ArrayList<AddToDoDAta>> {
        val gson = Gson()

        val type = object : TypeToken<HashMap<String, ArrayList<AddToDoDAta>>>() {}.type
        return gson.fromJson(str, type)


    }


}