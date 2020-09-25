package com.example.architecture_tutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.Model.ItemDatabase
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.adapter.View.ItemViewHolder
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ItemAdapter(val context : Context, var items : List<Item>) : RecyclerView.Adapter<ItemViewHolder>(){
    val db : ItemDatabase = ItemDatabase.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(context, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}