package com.example.ronote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnUsingViewModel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnUsingViewModel = findViewById(R.id.btn_using_view_model)
        btnUsingViewModel.setOnClickListener {
            startActivity(Intent(this, UsingViewModelActivity::class.java))
        }
    }
}