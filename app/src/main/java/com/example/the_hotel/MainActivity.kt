package com.example.the_hotel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btn1 : Button
    lateinit var btn2 : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn_get_start)
        btn2 = findViewById(R.id.btn_login)

        btn1.setOnClickListener{
            val intent_get_start = Intent(this,get_start::class.java)
            startActivity(intent_get_start)
        }

        btn2.setOnClickListener{
            val intent_login = Intent(this,login::class.java)
            startActivity(intent_login)
        }
    }
}