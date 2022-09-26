package com.example.ronote.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import androidx.navigation.Navigation
import com.example.ronote.R
import com.example.ronote.databinding.FragmentLoginBinding
import com.example.ronote.model.Users
import com.example.ronote.viewModel.RoNoteAddViewModel
import com.example.ronote.viewModel.ViewModelFactory

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var dataSharedPreference: SharedPreferences
    private lateinit var roNoteAddViewModel: RoNoteAddViewModel

    private var user: Users? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etPasswordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        roNoteAddViewModel = obtainViewModel(requireActivity())
        dataSharedPreference = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        user = Users()

        binding.btnLogin.setOnClickListener {
            observer(binding.etEmailInput.text.toString())
        }
        binding.tvGotoRegister.setOnClickListener {
            goRegister()
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): RoNoteAddViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RoNoteAddViewModel::class.java)
    }

    fun observer(email: String){
        val mainViewModel = obtainViewModel(requireActivity())
        mainViewModel.authUser(email).observe(requireActivity(), { userData ->
            if (userData != null){
                user!!.id = userData.id
                user!!.username = userData.username
                user!!.email = userData.email
                user!!.password = userData.password
                submitPreference(user!!.id.toString(), user!!.username.toString(), user!!.email.toString(), user!!.password.toString())
                auth(binding.etPasswordInput.text.toString())
            }
        })
    }

    fun auth(password: String){
        if (password.equals(dataSharedPreference.getString("password", "").toString())){
            Toast.makeText(context, "Login Sukses", Toast.LENGTH_SHORT).show()
            goHome()
        }else{
            Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show()
        }
    }

    fun submitPreference(id: String, username: String, email: String, password: String){
        var addData = dataSharedPreference.edit()
        addData.putString("id",id)
        addData.putString("username",username)
        addData.putString("email",email)
        addData.putString("password",password)
        addData.apply()
    }

    fun goHome(){
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
    }
    fun goRegister(){
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_signUpFragment)
    }
}