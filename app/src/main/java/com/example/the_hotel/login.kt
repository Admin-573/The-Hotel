package com.example.the_hotel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {

    lateinit var user_name_1 : EditText
    lateinit var user_pass_1 : EditText

    lateinit var fgtpass : TextView
    lateinit var sign_up_here : TextView

    lateinit var btn_signin : Button
    lateinit var btn_view : Button
    lateinit var btn_google : Button
    lateinit var btn_apple : Button

    private lateinit var sqLiteHelper: SQLiteHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sqLiteHelper = SQLiteHelper(this)

        user_name_1 = findViewById(R.id.user_name)
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
                val mainapp_intent = Intent(this,main_app::class.java)
                startActivity(mainapp_intent)
                val Usr = UserModel(name = user_name_1.toString(), pass = user_pass_1.toString())
                val status = sqLiteHelper.insertUser(Usr)
                if (status > -1){
                    Toast.makeText(this,"Added...",Toast.LENGTH_SHORT).show()
                    clearEditText()
                } else {
                    Toast.makeText(this,"Not Added...",Toast.LENGTH_SHORT).show()
                }
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

    private fun clearEditText(){
        user_name_1.setText("")
        user_pass_1.setText("")
        user_name_1.requestFocus()
    }

    private fun validation() : Boolean{

        if(user_name_1!!.length()==0){
            user_name_1!!.setError("UserName Cannot Be Empty !")
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