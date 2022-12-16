package com.ace.c11flight.ui.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ace.c11flight.R
import com.ace.c11flight.data.model.BookingViewModel
import com.ace.c11flight.data.model.TeenagerViewModel
import com.ace.c11flight.databinding.ActivityBookingBinding
import kotlin.math.min

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private val modelBooking:BookingViewModel by viewModels()
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
        withViewModel()
        viewmodelTeenager()
    }

    private fun viewmodelTeenager() {
        binding.btnPlusTeenager.setOnClickListener {
            aincrement()
        }
        binding.btnMinusTenager.setOnClickListener {
            bincrement()
        }
        modelBooking.valueBooking.observe(this) {
            result -> binding.tvValueTeenager.text = result.toString()
        }
    }

    private fun withViewModel() {
        binding.btnPlusAdult.setOnClickListener {
            aincrement()
        }
        binding.btnMinusPlus.setOnClickListener {
            bincrement()
        }
        modelBooking.valueBooking.observe(this) {result ->
            binding.tvValueAdult.text = result.toString()
        }
    }

    private fun bincrement() {
        modelBooking.valueBookingMin()
    }

    private fun aincrement(){
        modelBooking.valueBookingPlus()
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