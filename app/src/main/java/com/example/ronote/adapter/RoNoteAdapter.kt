package com.example.ronote.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ronote.R
import com.example.ronote.databinding.ItemRoNoteBinding
import com.example.ronote.helper.DateHelper
import com.example.ronote.helper.RoNoteDiffCallBack
import com.example.ronote.model.RoNote

class RoNoteAdapter(var listener: adapterListener): RecyclerView.Adapter<RoNoteAdapter.RoNoteViewHolder>() {

    private lateinit var context: Context
    val listRoNotes = ArrayList<RoNote>()

    fun setListRoNote(listRoNote: List<RoNote>){
        val diffCallBack = RoNoteDiffCallBack(this.listRoNotes,listRoNote)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.listRoNotes.clear()
        this.listRoNotes.addAll(listRoNote)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RoNoteViewHolder(private val binding: ItemRoNoteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(roNote: RoNote){
            binding.dataRoNote = roNote
            binding.ivEdit.setOnClickListener{
                val dialog = Dialog(context)
                dialog.setContentView(R.layout.dialog_add)
                dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                val judul: EditText = dialog.findViewById(R.id.etInputJudulDialogAdd)
                val desc: EditText = dialog.findViewById(R.id.etInputDescDialogAdd)
                val submit: Button = dialog.findViewById(R.id.btnAddRoNoteDialogAdd)

                judul.setText(roNote.title)
                desc.setText(roNote.description)

                submit.setOnClickListener {
                    roNote.let {roNote ->
                        roNote?.title = judul.text.toString()
                        roNote?.description = desc.text.toString()
                        roNote?.date = DateHelper.getCurrentDate()
                    }
                    listener.onUpdate(roNote)
                    dialog.dismiss()
                }
                dialog.show()

            }
            binding.ivDelete.setOnClickListener {
                val dialog = Dialog(context)
                dialog.setContentView(R.layout.dialog_delete)
                dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                val btnYes: Button = dialog.findViewById(R.id.btnYesDelete)
                val btnNo: Button = dialog.findViewById(R.id.btnNoDelete)

                btnYes.setOnClickListener {
                    Toast.makeText(context, "kehapus", Toast.LENGTH_SHORT).show()
                    listener.onDelete(roNote)
                    dialog.dismiss()
                }
                btnNo.setOnClickListener {
                    Toast.makeText(context, "gajadi hapus", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                dialog.show()
            }
            binding.cvListItemRoNote.setOnClickListener {
                val dialog = Dialog(context)
                dialog.setContentView(R.layout.dialog_detail)
                dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                val judul: EditText = dialog.findViewById(R.id.etSeeTitleDetailRoNote)
                val desc: EditText = dialog.findViewById(R.id.etSeeDescDetailRoNote)

                judul.setText(roNote.title)
                desc.setText(roNote.description)

                dialog.show()
            }
        }
    }

    interface adapterListener{
        fun onDelete(roNote: RoNote)
        fun onUpdate(roNote: RoNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoNoteViewHolder {
        val binding = ItemRoNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoNoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoNoteViewHolder, position: Int) {
        holder.bind(listRoNotes[position])
    }

    override fun getItemCount(): Int {
        return listRoNotes.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
}