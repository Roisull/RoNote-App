package com.example.ronote.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ronote.databinding.ItemRoNoteBinding
import com.example.ronote.model.RoNote

class RoNoteAdapter(var listener: adapterListener): RecyclerView.Adapter<RoNoteAdapter.RoNoteViewHolder>() {

    inner class RoNoteViewHolder(private val binding: ItemRoNoteBinding): RecyclerView.ViewHolder(binding.root){

    }

    interface adapterListener{
        fun onDelete(roNote: RoNote)
        fun onUpdate(roNote: RoNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoNoteViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RoNoteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}