package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityBookingBinding

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnclick()
    }

    private fun setOnclick() {
        binding.btnGobooking.setOnClickListener {
            val change = Intent(this@BookingActivity,MenuBooking::class.java)
            startActivity(change)
        }
    }


}