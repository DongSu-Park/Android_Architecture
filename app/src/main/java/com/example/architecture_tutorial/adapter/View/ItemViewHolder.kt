package com.example.architecture_tutorial.adapter.View

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_tutorial.Model.Item
import com.example.architecture_tutorial.R
import com.example.architecture_tutorial.adapter.ItemAdapter
import com.example.architecture_tutorial.adapter.Presenter.ItemViewHolderPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ItemViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
    var itemviewHolderPresenter = ItemViewHolderPresenter()
    private var btn_text_message = itemView.findViewById<Button>(R.id.btn_text)
    lateinit var targetDeleteDB :List<Item>

    fun bind(context : Context, item : Item){
        btn_text_message.text = "Input Text (${item.id}) : ${item.message}"

        btn_text_message.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
            builder.run {
                setTitle("삭제 메세지")
                setMessage("해당 메세지를 삭제하겠습니까? (Item id = ${item.id})")
                setPositiveButton("확인") { dialog, width ->
                    targetDeleteDB = itemviewHolderPresenter.deleteDB(context,  item.id, item.message)
                    ItemAdapter(context, targetDeleteDB).notifyDataSetChanged()
                    Toast.makeText(context, "삭제가 완료되었습니다", Toast.LENGTH_LONG).show()
                }
                setNegativeButton("취소") { dialog, width -> }
                show()
            }
        }
    }
}
