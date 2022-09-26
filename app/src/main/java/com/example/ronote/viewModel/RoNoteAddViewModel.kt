package com.example.ronote.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ronote.model.RoNote
import com.example.ronote.model.Users
import com.example.ronote.repository.RoNoteRepository

class RoNoteAddViewModel(application: Application): ViewModel() {
    private val manualRoNoteRepository: RoNoteRepository = RoNoteRepository(application)

    fun insert(roNote: RoNote){
        manualRoNoteRepository.insert(roNote)
    }
    fun update(roNote: RoNote){
        manualRoNoteRepository.update(roNote)
    }
    fun delete(roNote: RoNote){
        manualRoNoteRepository.delete(roNote)
    }
    fun getAllNotes(idUser: String): LiveData<List<RoNote>> = manualRoNoteRepository.getAllRoNotes(idUser)

    fun insertUser(users: Users){
        manualRoNoteRepository.insertUser(users)
    }
    fun updateUser(users: Users){
        manualRoNoteRepository.updateUser(users)
    }
    fun deleteUser(users: Users){
        manualRoNoteRepository.deleteUser(users)
    }
    fun authUser(email: String): LiveData<Users> = manualRoNoteRepository.authUser(email)
}