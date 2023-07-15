package com.example.the_hotel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.the_hotel.databinding.ActivityLoginBinding
import com.example.the_hotel.databinding.ActivityMainAppBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class login : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //createInstance of firebaseAuthentication
        firebaseAuth=FirebaseAuth.getInstance()
        val googleSigninOption= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,googleSigninOption)

        binding.forgotPassword.setOnClickListener{
            Toast.makeText(this,"Try To Remember Than ! 😉",Toast.LENGTH_SHORT).show()
        }

        binding.btnSignIn.setOnClickListener{
            if(validation())
            {
                val mainapp_intent = Intent(this,main_app::class.java)
                startActivity(mainapp_intent)
            }
        }


        binding.btnGoogleSignIn.setOnClickListener{
            googleSignIn()
        }

        binding.signUp.setOnClickListener{
            startActivity(Intent(this,SignUpUser::class.java))
        }

    }

    private fun googleSignIn() {
        val signInIntent=googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode== Activity.RESULT_OK){
            val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task : Task<GoogleSignInAccount>){
        if (task.isSuccessful){
            val account:GoogleSignInAccount?=task.result
            if(account!=null){
                updateUI(account)
            }
        }else{
            showToast(task.exception.toString())
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast("Successfully Sign In")
                    startActivity(Intent(applicationContext,main_app::class.java))
                }else{
                    showToast(it.exception.toString())
                }
            }
    }

    private fun showToast(str: String) {
        Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
    }

    private fun validation() : Boolean{

        if(binding.userName.length()==0){
            binding.userName.setError("UserName Cannot Be Empty !")
            return false
        } else if (binding.userPassword.length() == 0){
            binding.userPassword.setError("Password Cannot Be Empty !")
            return false
        } else if (binding.userPassword.length() < 8){
            binding.userPassword.setError("Password Length Must Of 8 Chars !")
            return false
        }
        return true
    }
}