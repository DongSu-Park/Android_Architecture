package com.example.architecture_tutorial.Presenter

import android.content.Context

interface Presenter{

    fun loadDB(context: Context)

    fun addDB(context: Context)

    fun clearDB(context: Context)

}