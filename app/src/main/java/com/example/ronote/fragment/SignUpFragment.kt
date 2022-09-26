package com.example.ronote.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.ronote.R
import com.example.ronote.databinding.FragmentSignUpBinding
import com.example.ronote.model.Users
import com.example.ronote.viewModel.RoNoteAddViewModel
import com.example.ronote.viewModel.ViewModelFactory

class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    private lateinit var roNoteAddViewModel: RoNoteAddViewModel

    private var users: Users? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roNoteAddViewModel = obtainViewModel(requireActivity())
        users = Users()

        binding.btnRegister.setOnClickListener {
            when{
                binding.etRegisterUsername.text.toString().isEmpty()->{
                    binding.etRegisterUsername.error = "Filled Username"
                }
                binding.etRegisterEmail.text.toString().isEmpty()->{
                    binding.etRegisterEmail.error = "Filled Email"
                }
                binding.etRegisterPassword.text.toString().isEmpty()->{
                    binding.etRegisterPassword.error = "Filled Password"
                }
                binding.etRegisterConfirmPassword.text.toString().isEmpty()->{
                    binding.etRegisterConfirmPassword.error = "Filled Confirm Password"
                }
                else ->{
                    users.let { note ->
                        note?.username = binding.etRegisterUsername.text.toString()
                        note?.email = binding.etRegisterEmail.text.toString()
                        note?.password = binding.etRegisterPassword.text.toString()
                    }
                    roNoteAddViewModel.insertUser(users as Users)
                    Toast.makeText(context, "Sign Up Sukses, Login Know", Toast.LENGTH_SHORT).show()
                    goLogin()
                }
            }
        }

        binding.tvGotoLogin.setOnClickListener {
            goLogin()
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): RoNoteAddViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RoNoteAddViewModel::class.java)
    }
    fun goLogin(){
        Navigation.findNavController(requireView()).navigate(R.id.action_signUpFragment_to_loginFragment)
    }
}