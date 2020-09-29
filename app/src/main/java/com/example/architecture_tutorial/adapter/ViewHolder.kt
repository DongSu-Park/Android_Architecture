package com.example.architecture_tutorial.adapter

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.databinding.ItemListBinding
import com.example.architecture_tutorial.model.Item
import com.example.architecture_tutorial.viewModel.ItemViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ViewHolder(binding: ItemListBinding, context: Context): RecyclerView.ViewHolder(binding.root) {
    private lateinit var holderViewModel : ItemViewModel
    private var binding = binding
    private var dialogContext = context

    private var selectId : Int? = null
    private var selectMessage : String? = null
    var buttonTextMessage : String = ""

    fun bind(targetId : Int? , targetMessage : String, viewModel: ItemViewModel) {
        holderViewModel = viewModel

        binding.run{
            itemViewList = this@ViewHolder
            itemViewModel = viewModel
        }

        buttonTextMessage = "Input Text (${targetId}) : $targetMessage"
        selectId = targetId
        selectMessage = targetMessage
    }

    fun deleteDialog() {
        val builder = MaterialAlertDialogBuilder(dialogContext, R.style.CustomMaterialDialog)

        builder.run {
            setTitle("삭제 메세지")
            setMessage("해당 메세지를 삭제하겠습니까? (Item id = ${selectId})")
            setPositiveButton("확인") { dialog, width ->
                holderViewModel.delete(Item(selectId, selectMessage!!))
                Toast.makeText(context, "삭제가 완료되었습니다", Toast.LENGTH_LONG).show()
            }
            setNegativeButton("취소") { dialog, width ->
            }
            show()
        }
    }

}