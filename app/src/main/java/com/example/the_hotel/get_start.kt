package com.example.the_hotel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

@SuppressLint("MissingInflatedId")
class get_start : AppCompatActivity() {

    lateinit var btn_signup : Button
    lateinit var btn_google_done : Button
    lateinit var btn_apple_done : Button

    lateinit var edfname : EditText
    lateinit var edlname : EditText
    lateinit var email : EditText
    lateinit var phone : EditText
    lateinit var pass : EditText
    lateinit var cnf_pass : EditText

    lateinit var txt_sign_in_1 : TextView

    var isAllFieldsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_start)

        btn_signup = findViewById(R.id.btn_signup)
        btn_google_done = findViewById(R.id.btn_google_selection)
        btn_apple_done = findViewById(R.id.btn_apple_selection)

        edfname = findViewById(R.id.edtFName)
        edlname = findViewById(R.id.edtLName)
        email = findViewById(R.id.edtEmail)
        phone = findViewById(R.id.edtPhone)
        pass = findViewById<EditText>(R.id.edtPass)
        cnf_pass = findViewById<EditText>(R.id.edt_cnf_pass)

        txt_sign_in_1 = findViewById(R.id.txt_sign_in)

        btn_signup.setOnClickListener{
            isAllFieldsChecked = checkallfields()
            if(isAllFieldsChecked){
                val intent_home_screen = Intent(this,home::class.java)
                startActivity(intent_home_screen)
            }
        }

        btn_google_done.setOnClickListener{
            Toast.makeText(this,"Under Development !",Toast.LENGTH_SHORT).show()
        }

        btn_apple_done.setOnClickListener{
            Toast.makeText(this,"Under Development !",Toast.LENGTH_SHORT).show()
        }

        txt_sign_in_1.setOnClickListener{
            val go_to_signin = Intent(this,login::class.java)
            startActivity(go_to_signin)
        }

    }

    private fun checkallfields() : Boolean {

        if (edfname!!.length()==0){
            edfname!!.setError("First Name Is Required !")
            return false
        } else if(edlname!!.length()==0){
            edlname!!.setError("Last Name Is Required !")
            return false
        } else if(email!!.length()==0){
            email!!.setError("Email Is Required !")
            return false
        } else if(phone!!.length()==0){
            phone!!.setError("Phone No Is Required !")
            return false
        } else if(pass!!.length() == 0){
            pass!!.setError("Password Is Required !")
            return false
        } else if(cnf_pass!!.length() == 0){
            cnf_pass!!.setError("ReEnter Your Password !")
            return false
        } else if(pass.text.toString() != cnf_pass.text.toString()) {
            cnf_pass.setError("Both Password Should Be Same !")
            return false
        }
        return true;
    }
}
