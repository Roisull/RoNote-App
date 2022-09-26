package com.example.ronote.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory{
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(application)
                }
            }

            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(RoNoteAddViewModel::class.java)){
            return RoNoteAddViewModel(mApplication) as T
        }
        throw IllegalArgumentException("tidak ada class ViewModel: ${modelClass.name}")
    }
}