package com.example.the_hotel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {

    lateinit var user_email_1 : EditText
    lateinit var user_pass_1 : EditText

    lateinit var fgtpass : TextView
    lateinit var sign_up_here : TextView

    lateinit var btn_signin : Button
    lateinit var btn_google : Button
    lateinit var btn_apple : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_email_1 = findViewById(R.id.user_email)
        user_pass_1 = findViewById(R.id.user_password)

        fgtpass = findViewById(R.id.forgot_pass)
        sign_up_here = findViewById(R.id.txt_signup)

        btn_signin = findViewById(R.id.btn_SignIn)
        btn_google = findViewById(R.id.btn_google_signin)
        btn_apple = findViewById(R.id.btn_apple_signin)

        fgtpass.setOnClickListener{
            Toast.makeText(this,"Try To Remember Than ! 😉",Toast.LENGTH_SHORT).show()
        }

        btn_signin.setOnClickListener{
            if(validation())
            {
                val home_intent = Intent(this,home::class.java)
                startActivity(home_intent)
            }
        }

        btn_google.setOnClickListener{
            Toast.makeText(this,"Under Development",Toast.LENGTH_SHORT).show()
        }

        btn_apple.setOnClickListener{
            Toast.makeText(this,"Under Development",Toast.LENGTH_SHORT).show()
        }

        sign_up_here.setOnClickListener{
            Toast.makeText(this,"Under Development",Toast.LENGTH_SHORT).show()
        }

    }

    private fun validation() : Boolean{

        if(user_email_1!!.length()==0){
            user_email_1!!.setError("Email Cannot Be Empty !")
            return false
        } else if (user_pass_1!!.length() == 0){
            user_pass_1!!.setError("Password Cannot Be Empty !")
            return false
        } else if (user_pass_1!!.length() < 8){
            user_pass_1!!.setError("Password Length Must Of 8 Chars !")
            return false
        }
        return true
    }
}