package com.example.architecture_tutorial.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.ViewModel.ItemViewModel
import com.example.architecture_tutorial.adapter.ItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application!!)).get(ItemViewModel::class.java)

        val itemAdapter = ItemAdapter(this, itemViewModel)
        val lm = LinearLayoutManager(this)

        layout_recyclerView.run{
            adapter = itemAdapter
            layoutManager = lm
            setHasFixedSize(true)
        }



        itemViewModel.getAll().observe(this, Observer<List<Item>>{ items ->
            itemAdapter.setItems(items)
        })

        btn_input.setOnClickListener {
            val id : Int? = null
            val newMessage : String = et_input.text.toString()

            val item = Item(id, newMessage)
            itemViewModel.insert(item)
        }

        btn_clear.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this, R.style.CustomMaterialDialog)
            builder.run {
                setTitle("삭제 메세지")
                setMessage("전체 메세지를 삭제하겠습니까?")
                setPositiveButton("확인") { dialog, width ->
                    itemViewModel.allDelete()
                }
                setNegativeButton("취소") { dialog, width -> }
                show()
            }
        }
    }
}