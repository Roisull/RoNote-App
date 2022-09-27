package com.example.ronote.fragment

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ronote.R
import com.example.ronote.adapter.RoNoteAdapter
import com.example.ronote.databinding.DialogAddBinding
import com.example.ronote.databinding.FragmentHomeBinding
import com.example.ronote.helper.DateHelper
import com.example.ronote.model.RoNote
import com.example.ronote.model.Users
import com.example.ronote.viewModel.RoNoteAddViewModel
import com.example.ronote.viewModel.ViewModelFactory

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var dialogAddBinding: DialogAddBinding
    private lateinit var roNoteAddViewModel: RoNoteAddViewModel
    lateinit var dataSharedPreference: SharedPreferences

    private var roNote: RoNote? = null
    private var users: Users? = null

    private lateinit var adapter: RoNoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var  context = binding.rvRoNote.context

        roNoteAddViewModel = obtainViewModel(requireActivity())
        roNote = RoNote()
        users = Users()
        dialogAddBinding = DialogAddBinding.inflate(layoutInflater)
        dataSharedPreference = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        getDataRoNote()
        setAdapter()

        binding.FbAddBerita.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_add)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val judul: EditText = dialog.findViewById(R.id.etInputJudulDialogAdd)
            val desc: EditText = dialog.findViewById(R.id.etInputDescDialogAdd)
            val submit: Button = dialog.findViewById(R.id.btnAddRoNoteDialogAdd)

            submit.setOnClickListener {
                when{
                    judul.text.toString().isEmpty() -> {
                        Toast.makeText(context, "Title is Empty", Toast.LENGTH_SHORT).show()
                    }
                    desc.text.toString().isEmpty() -> {
                        Toast.makeText(context, "RoNote is Empty", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        roNote.let {  roNote ->
                            roNote?.title = judul.text.toString()
                            roNote?.description = desc.text.toString()
                            roNote?.date = DateHelper.getCurrentDate()
                            roNote?.idUser = users!!.id
                        }
                        roNoteAddViewModel.insert(roNote as RoNote)
                        Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                        observer()
                    }
                }
            }
            dialog.show()
        }
        binding.tvLogout.setOnClickListener {
            clearData()
            goLogin()
            Toast.makeText(context, "LogOut", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDataRoNote(){
        users.let { user ->
            user?.id = Integer.parseInt(dataSharedPreference.getString("id",""))
            user?.username = dataSharedPreference.getString("username","")
            user?.email = dataSharedPreference.getString("email","")
            user?.password = dataSharedPreference.getString("password","")
        }
        binding.tvInfoUser.setText("Selamat Datang, ${users?.username}")
    }
    private fun obtainViewModel(activity: FragmentActivity): RoNoteAddViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RoNoteAddViewModel::class.java)
    }
    fun setAdapter(){
        adapter = RoNoteAdapter(
            object : RoNoteAdapter.adapterListener{
                override fun onUpdate(roNote: RoNote) {
                    roNoteAddViewModel.update(roNote)
                    Toast.makeText(context, "Ter-Update", Toast.LENGTH_SHORT).show()
                    observer()
                }

                override fun onDelete(roNote: RoNote) {
                    roNoteAddViewModel.delete(roNote)
                    Toast.makeText(context, "Ter-Delete", Toast.LENGTH_SHORT).show()
                    observer()
                }
            }
        )

        observer()
    }
    fun observer(){
        val mainViewModel = obtainViewModel(requireActivity())
        mainViewModel.getAllNotes(users?.id.toString()).observe(requireActivity(), { roNoteList ->
            if (roNoteList.size != null){
                adapter.setListRoNote(roNoteList)
            }
        })
        binding?.rvRoNote?.layoutManager = LinearLayoutManager(context)
        binding?.rvRoNote?.setHasFixedSize(true)
        binding?.rvRoNote?.adapter = adapter
    }

    fun clearData(){
        var pref = dataSharedPreference.edit()
        pref.clear()
        pref.apply()
    }
    fun goLogin(){
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_loginFragment)
    }
}