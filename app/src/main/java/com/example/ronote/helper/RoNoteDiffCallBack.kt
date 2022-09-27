package com.example.ronote.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.ronote.model.RoNote

class RoNoteDiffCallBack(private val mOldRoNoteList: List<RoNote>, private val mNewRoNoteList: List<RoNote>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldRoNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewRoNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldRoNoteList[oldItemPosition].id == mNewRoNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmploye = mOldRoNoteList[oldItemPosition]
        val newEmploye = mNewRoNoteList[newItemPosition]
        return oldEmploye.title == newEmploye.title && oldEmploye.description == newEmploye.description
    }

}