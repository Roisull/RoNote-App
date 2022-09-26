package com.example.ronote.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import com.example.ronote.model.Users

class UserViewModel  {
    val isStringEmpty = MutableLiveData<Boolean>()
    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputEmail = MutableLiveData<String>()
    @Bindable
    val inputPassword = MutableLiveData<String>()

    val listData = MutableLiveData<ArrayList<Users>>()
    private val arrayLst = ArrayList<Users>()

    init {
        isStringEmpty.value = false
    }

    fun addData(){
        val name = inputName.value
        val email = inputEmail.value
        val password = inputPassword.value
        if (name!!.isBlank() || email!!.isBlank() || password!!.isBlank()){
            isStringEmpty.value = true
        }else{
            inputName.value = " "
            inputEmail.value = " "
            inputPassword.value = " "
            var users = Users(name,email,password)
            arrayLst.add(users)
            listData.value = arrayLst
        }
    }

    fun clearData(){
        arrayLst.clear()
        listData.value = arrayLst
    }
}