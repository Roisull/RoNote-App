package com.example.ronote.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class RoNote (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "date")
    var date: String? = null,
    @ColumnInfo(name = "idUser")
    var idUser: Int = 0,
): Parcelable