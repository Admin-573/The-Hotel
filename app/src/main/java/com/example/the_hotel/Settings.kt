package com.example.the_hotel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.the_hotel.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth

class Settings : Fragment() {

    private lateinit var  binding: FragmentSettingsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        binding= FragmentSettingsBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.topAppBar.setOnMenuItemClickListener{
            menuItem -> when(menuItem.itemId){
                R.id.SignOut  -> {
                    firebaseAuth.signOut()
                    Toast.makeText(context,"Sign Out",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context,login::class.java))
                    true
                }
            R.id.Contact -> {
                Toast.makeText(context,"Under development",Toast.LENGTH_SHORT).show()
                true
            }

            else -> {
                false
            }
        }
        }
        return binding.root
    }

}