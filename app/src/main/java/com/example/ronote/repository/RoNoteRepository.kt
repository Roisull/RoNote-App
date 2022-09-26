package com.example.ronote.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.ronote.database.RoNoteDAO
import com.example.ronote.database.RoNoteRoomDatabase
import com.example.ronote.model.RoNote
import com.example.ronote.model.Users
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RoNoteRepository(application: Application) {
    private val m: RoNoteDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = RoNoteRoomDatabase.getDatabase(application)
        m = db.roNoteDao()
    }

    fun getAllRoNotes(idUSer: String): LiveData<List<RoNote>> = m.getAllRoNote(idUSer)
    fun insert(roNote: RoNote){
        executorService.execute { m.insertRoNote(roNote) }
    }
    fun delete(roNote: RoNote){
        executorService.execute { m.deleteRoNote(roNote) }
    }
    fun update(roNote: RoNote){
        executorService.execute { m.updateRoNote(roNote) }
    }

    fun insertUser(users: Users){
        executorService.execute { m.insertUserRoNote(users) }
    }
    fun deleteUser(users: Users){
        executorService.execute { m.deleteUserRoNote(users) }
    }
    fun updateUser(users: Users){
        executorService.execute { m.updateUserRoNote(users) }
    }
    fun authUser(email: String): LiveData<Users> = m.autUser(email)
}