package com.example.architecture_tutorial.adapter.Presenter

import android.content.Context
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.Model.ItemDatabase

class ItemViewHolderPresenter() : ViewHolderPresenter{
    lateinit var items : List<Item>

    override fun deleteDB(context : Context, targetId : Int?, targetMessage: String?): List<Item> {
        val db : ItemDatabase = ItemDatabase.getInstance(context)
        db.itemDao().delete(Item(targetId, targetMessage))

        items = db.itemDao().getAll()
        return items
    }

}