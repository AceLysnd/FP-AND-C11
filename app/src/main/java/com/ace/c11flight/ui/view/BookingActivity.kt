package com.ace.c11flight.ui.view

import android.content.Intent
import android.graphics.Color
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

        val extras = intent.extras

//        if (extras != null) {
//            getDetails(extras)
//
//        } else {
//            finish()
//        }
        setOnclick()
    }

    private fun setOnclick() {
        binding.btnOneway.setOnClickListener {
            binding.btnOneway.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnRoundtrip.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnRoundtrip.setOnClickListener {
            binding.btnRoundtrip.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnOneway.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnEconomy.setOnClickListener {
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnBussiness.setOnClickListener {
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnFirstclass.setOnClickListener {
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
    }


}