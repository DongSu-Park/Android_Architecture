package com.example.architecture_tutorial.Presenter

import android.content.Context
import com.example.architecture_tutorial.Model.Item

interface Presenter{

    fun loadDB(context: Context): List<Item>

    fun addDB(context: Context, newId : Int?, newMessage : String): List<Item>

    fun clearDB(context: Context): List<Item>

}