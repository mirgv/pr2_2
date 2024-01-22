package com.bignerdranch.android.labs11

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

var Weath : MutableList<Weather> = mutableListOf()
private lateinit var DateTime: EditText
private lateinit var Daytime_Temperature: EditText
private lateinit var NightTemp: EditText
private lateinit var btn: Button
class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        getWeather()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        DateTime = findViewById(R.id.editTextTextPersonName)
        Daytime_Temperature = findViewById(R.id.editTextTextPersonName2)
        NightTemp = findViewById(R.id.editTextTextPersonName3)
        btn = findViewById(R.id.button3)

        btn.setOnClickListener {
            addWeather(DateTime.text.toString(), Daytime_Temperature.text.toString(), NightTemp.text.toString())
        }



    }

    private fun getWeather()
    {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if(!preferences.contains("json"))
        {
            return
        }
        else {
            json = preferences.getString("json","NOT_JSON").toString()

        }
        val weathList = Gson().fromJson<List<Weather>>(json ,object: TypeToken<List<Weather>>(){}.type)
        Weath.addAll(weathList)

    }
    private fun addWeather(dateTime:String,daytime_temp:String,nightTemp:String){
        val Temp = Weather(dateTime,daytime_temp,nightTemp)
        Weath.add(Temp)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        preferences.edit{
            this.putString("json",Gson().toJson(Weath).toString())
        }
    }
}