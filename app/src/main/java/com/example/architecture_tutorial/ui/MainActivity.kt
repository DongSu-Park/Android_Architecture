package com.example.architecture_tutorial.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.viewModel.ItemViewModel
import com.example.architecture_tutorial.adapter.ItemAdapter
import com.example.architecture_tutorial.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.itemviewmodel = viewModel

        // Layout Setting
        val itemAdapter = ItemAdapter(this, viewModel)

        layout_recyclerView.run {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        // LiveData Setting
        viewModel.getAll().observe(this, Observer { items ->
            itemAdapter.setItems(items)
        })

    }

    fun clearBtn(){
        val builder = MaterialAlertDialogBuilder(this, R.style.CustomMaterialDialog)
        builder.run {
            setTitle("삭제 메세지")
            setMessage("전체 메세지를 삭제하겠습니까?")
            setPositiveButton("확인") { dialog, width ->
                viewModel.allDelete()
            }
            setNegativeButton("취소") { dialog, width -> }
            show()
        }
    }
}