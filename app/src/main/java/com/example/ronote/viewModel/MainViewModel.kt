package com.example.ronote.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.ronote.repository.RoNoteRepository

class MainViewModel (application: Application): ViewModel() {
    private val mRoNoteRepository: RoNoteRepository = RoNoteRepository(application)
}