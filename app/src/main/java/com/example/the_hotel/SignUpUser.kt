package com.example.the_hotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.the_hotel.databinding.SignUpUserBinding

class SignUpUser : AppCompatActivity() {
    private lateinit var binding: SignUpUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener{
            if(validation()){
                startActivity(Intent(this,main_app::class.java))
            }
        }
        binding.alreadyHaveAnAccount.setOnClickListener {
            startActivity(Intent(this,login::class.java))
        }

    }
    private fun validation() : Boolean{

        if(binding.edtname.length()==0){
            binding.edtname.setError("Name Cannot Be Empty !")
            return false
        } else if (binding.edtemail.length() == 0){
            binding.edtemail.setError("Email Cannot Be Empty !")
            return false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.edtemail.text.toString()).matches()){
            binding.edtemail.setError("Email format Invalid !")
            return false
        }else if (binding.edtphone.length() < 10 ){
            binding.edtphone.setError("Enter Valid Phone No")
            return false
        }else if(binding.edtpassword.length() < 6){
            binding.edtpassword.setError("Password Length is 6 char")
            return false
        }
        return true
    }
}