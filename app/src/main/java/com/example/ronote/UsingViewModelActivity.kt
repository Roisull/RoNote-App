package com.example.ronote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.ronote.viewModel.ViewModelExample

class UsingViewModelActivity : AppCompatActivity() {

    // 1. memanggil view modelnya ke dalam activity nya
    lateinit var etPanjang: EditText
    lateinit var etLebar: EditText
    lateinit var etTinggi: EditText
    lateinit var btnHitung: Button
    lateinit var tvResult: TextView
    lateinit var viewModel: ViewModelExample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_using_view_model)

        etPanjang = findViewById(R.id.et_pjg)
        etLebar = findViewById(R.id.et_lbr)
        etTinggi = findViewById(R.id.et_tgi)
        btnHitung = findViewById(R.id.btn_hitung_using_view_model)
        tvResult = findViewById(R.id.tv_result_hitung)

        // call view model
        viewModel = ViewModelProvider(this).get(ViewModelExample::class.java)
        tvResult.text = viewModel.hasil.toString()

        btnHitung.setOnClickListener {
            var pj = etPanjang.text.toString().toInt()
            var lb = etLebar.text.toString().toInt()
            var tg = etTinggi.text.toString().toInt()

            viewModel.hitungData(pj,lb,tg)

            tvResult.text = viewModel.hasil.toString()
        }

    }
}