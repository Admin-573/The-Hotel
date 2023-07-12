package com.example.the_hotel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class main_app : AppCompatActivity() {

    lateinit var bottom_nav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        loadFragment(Hotel())

        bottom_nav = findViewById(R.id.botom_nav)
        bottom_nav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.hotel -> {
                    loadFragment(Hotel())
                    true
                }
                R.id.profile -> {
                    loadFragment(Profile())
                    true
                }
                R.id.settings -> {
                    loadFragment(Settings())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,fragment)
        transaction.commit()
    }
}