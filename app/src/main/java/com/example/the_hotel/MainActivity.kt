package com.example.the_hotel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ncorti.slidetoact.SlideToActView
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    lateinit var sta : SlideToActView

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //next Activity :-
        sta = findViewById(R.id.btn_get_start)
        sta.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                val intent_login = Intent(applicationContext,login::class.java)
                startActivity(intent_login)
            }
        }

        //imageSlider :-
        init()
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,1000)
            }
        })

    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,1000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{ page,position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    private fun init()
    {
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.hotel1)
        imageList.add(R.drawable.hotel2)
        imageList.add(R.drawable.hotel4)
        imageList.add(R.drawable.hotel8)
        imageList.add(R.drawable.hotel9)
        imageList.add(R.drawable.hotel10)

        adapter = ImageAdapter(imageList,viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 2
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }
}