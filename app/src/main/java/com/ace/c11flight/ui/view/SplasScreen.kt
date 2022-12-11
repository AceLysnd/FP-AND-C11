package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivitySplasScreenBinding

class SplasScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplasScreenBinding
    private val SPLASH_TIME_OUT:Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplasScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler().postDelayed({
            val intent = Intent(this@SplasScreen, LoginActivity::class.java)
            startActivity(intent)

        },SPLASH_TIME_OUT)


    }


    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}