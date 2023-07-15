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

    lateinit var user_name_1 : EditText
    lateinit var user_pass_1 : EditText

    lateinit var fgtpass : TextView
    lateinit var sign_up_here : TextView

    lateinit var btn_signin : Button
    lateinit var btn_google : Button

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //createInstance of firebaseAuthentication
        firebaseAuth=FirebaseAuth.getInstance()
        val googleSigninOption= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,googleSigninOption)

        user_name_1 = findViewById(R.id.user_name)
        user_pass_1 = findViewById(R.id.user_password)

        fgtpass = findViewById(R.id.forgot_pass)
        sign_up_here = findViewById(R.id.txt_signup)

        btn_signin = findViewById(R.id.btn_SignIn)
        btn_google = findViewById(R.id.btn_google_signin)

        fgtpass.setOnClickListener{
            Toast.makeText(this,"Try To Remember Than ! 😉",Toast.LENGTH_SHORT).show()
        }

        btn_signin.setOnClickListener{
            if(validation())
            {
                val mainapp_intent = Intent(this,main_app::class.java)
                startActivity(mainapp_intent)
            }
        }


        btn_google.setOnClickListener{
            googleSignIn()
        }

        sign_up_here.setOnClickListener{
            Toast.makeText(this,"Under Development",Toast.LENGTH_SHORT).show()
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

        if(user_name_1.length()==0){
            user_name_1.setError("UserName Cannot Be Empty !")
            return false
        } else if (user_pass_1.length() == 0){
            user_pass_1.setError("Password Cannot Be Empty !")
            return false
        } else if (user_pass_1.length() < 8){
            user_pass_1.setError("Password Length Must Of 8 Chars !")
            return false
        }
        return true
    }
}