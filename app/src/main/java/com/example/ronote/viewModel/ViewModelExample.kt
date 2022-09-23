package com.example.ronote.viewModel

import androidx.lifecycle.ViewModel

class ViewModelExample: ViewModel() {

    var hasil = 0

    fun hitungData(panjang: Int, lebar: Int, tinggi:Int){
        hasil = panjang * lebar * tinggi
    }
}