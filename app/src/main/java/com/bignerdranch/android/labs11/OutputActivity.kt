package com.bignerdranch.android.labs11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OutputActivity : AppCompatActivity() {
    val Weath : MutableList<Weather> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)
        getWeath()
        Weath.forEach{
            Log.d("fd",it.toString())
        }

    }
    private fun getWeath()
    {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json : String = ""
        if(!preferences.contains("json")){
            return
        }
        else
        {
            json = preferences.getString("json","NOT_JSON").toString()
            val WeathList = Gson().fromJson<List<Weather>>(json, object: TypeToken<List<Weather>>(){}.type)
            Weath.addAll(WeathList)
        }
    }
}