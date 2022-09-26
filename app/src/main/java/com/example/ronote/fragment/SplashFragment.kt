package com.example.ronote.fragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.Navigation
import com.example.ronote.R
import com.example.ronote.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var dataSharedPreference: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // untuk mengambil data user dan di masukkan ke sharedPreference
        dataSharedPreference = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)

        // control flow untuk ngecek apakah user sudah login atau belum, yang ngecek adalah Si SharedPreference
        android.os.Handler(Looper.myLooper()!!).postDelayed({
            if (dataSharedPreference.getString("id","").equals("")){
                goLogin()
            }else{
                goHome()
            }
        }, 3000)
    }

    // function untuk pergi ke fragment login menggunakan navigation component
    fun goLogin(){
        Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment)
    }
    // function untuk pergi ke fragment home menggunakan navigation component
    fun goHome(){
        Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
    }
}