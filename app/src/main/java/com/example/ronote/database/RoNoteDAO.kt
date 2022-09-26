package com.example.ronote.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ronote.model.RoNote
import com.example.ronote.model.Users

@Dao
interface RoNoteDAO {

    // DAO Untuk CRUD dari model RoNote
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRoNote(roNote: RoNote)
    @Update
    fun updateRoNote(roNote: RoNote)
    @Delete
    fun deleteRoNote(roNote: RoNote)
    @Query("SELECT * FROM RoNote WHERE idUser LIKE :idUser ORDER BY id ASC ")
    fun getAllRoNote(idUser: String): LiveData<List<RoNote>>

    // DAO Untuk CRUD dari model Users
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserRoNote(users: Users)
    @Update
    fun updateUserRoNote(users: Users)
    @Delete
    fun deleteUserRoNote(users: Users)
    @Query("SELECT * FROM Users WHERE email LIKE :email")
    fun autUser(email: String): LiveData<Users>
}