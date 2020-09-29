package com.example.architecture_tutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_tutorial.model.Item
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.databinding.ItemListBinding
import com.example.architecture_tutorial.viewModel.ItemViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ItemAdapter(val context: Context, viewModel: ItemViewModel) : RecyclerView.Adapter<ViewHolder>(){
    private var items: List<Item> = listOf()
    private var itemViewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position].id, items[position].message, itemViewModel)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Item>){
        this.items = items
        notifyDataSetChanged()
    }
}