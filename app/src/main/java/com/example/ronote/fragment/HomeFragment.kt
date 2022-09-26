package com.example.ronote.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ronote.R
import com.example.ronote.databinding.DialogAddBinding
import com.example.ronote.databinding.FragmentHomeBinding
import com.example.ronote.model.RoNote
import com.example.ronote.model.Users
import com.example.ronote.viewModel.RoNoteAddViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var dialogAddBinding: DialogAddBinding
    private lateinit var roNoteAddViewModel: RoNoteAddViewModel
    lateinit var dataSharedPreference: SharedPreferences

    private var roNote: RoNote? = null
    private var users: Users? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}