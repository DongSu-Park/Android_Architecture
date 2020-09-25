package com.example.architecture_tutorial.Presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.Model.ItemDatabase
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.UI.MainActivity
import com.example.architecture_tutorial.adapter.ItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter(private val view: MainActivity) : Presenter {
    lateinit var items : List<Item>

    override fun loadDB(context : Context): List<Item> {
        val db : ItemDatabase = ItemDatabase.getInstance(context)
        items = db.itemDao().getAll()

        return items
        }

    override fun addDB(context: Context, newId : Int?, newMessage : String): List<Item> {
        val db : ItemDatabase = ItemDatabase.getInstance(context)
        val newItem = Item(newId, newMessage)

        db.itemDao().insert(newItem)
        items = db.itemDao().getAll()

        return items
    }

    override fun clearDB(context: Context): List<Item> {
        val db : ItemDatabase = ItemDatabase.getInstance(context)
        db.itemDao().allDelete()
        items = db.itemDao().getAll()

        return items
    }
}