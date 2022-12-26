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
import com.ace.c11flight.ui.view.HomeFragment.Companion.REQUEST_SOURCE
import kotlin.math.min

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private val modelBooking:BookingViewModel by viewModels()
    private val modelTeenager: BookingViewModel by viewModels()
    private val modelChild : BookingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnclick()
        withViewModel()
        viewmodelTeenager()
        viewModelChild()
        goTowishlist()

        initData()
        binding.btnSearchFlight.setOnClickListener {
            val intent = Intent(this@BookingActivity,TicketActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initData() {
        binding.tvFromCode.text = AIRPORT_CODE_FROM
        binding.etFrom.hint = AIRPORT_CITY_FROM
        binding.tvToCode.text = AIRPORT_CODE_TO
        binding.etTo.hint = AIRPORT_CITY_TO
    }

    private fun goTowishlist() {
        binding.btnWislist.setOnClickListener {
            val intent = Intent(this@BookingActivity,WishlistActivity::class.java)
            startActivity(intent)
        }
    }


    private fun viewModelChild() {
        binding.btnPlusChildren.setOnClickListener {
            valuePlusChild()
        }
        binding.btnMinusChildreen.setOnClickListener {
            valueMinChild()
        }
        modelChild.chilViewModel.observe(this) {
            result -> binding.tvValueChildreen.text = result.toString()
        }
    }

    private fun valueMinChild() {
        modelChild.minvalueChild()
    }

    private fun valuePlusChild() {
        modelChild.plusValuechild()
    }

    private fun viewmodelTeenager() {
        binding.btnPlusTeenager.setOnClickListener {
            valuePlusTeenager()
        }
        binding.btnMinusTenager.setOnClickListener {
            valueMinTeenager()
        }
        modelTeenager.valueTeenager.observe(this) {
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

    private fun valueMinTeenager() {
        modelTeenager.minValueTeenager()
    }
    private fun valuePlusTeenager() {
        modelTeenager.plusValueTeenager()
    }
    private fun bincrement() {
        modelBooking.valueBookingMin()
    }

    private fun aincrement(){
        modelBooking.valueBookingPlus()
    }

    private fun setOnclick() {

        binding.tvFromCode.setOnClickListener{
            AIRPORT_COUNTER = 1
            REQUEST_SOURCE = 1
            intent = Intent(this, AirportListActivity::class.java)
            startActivity(intent)
        }

        binding.tvToCode.setOnClickListener {
            AIRPORT_COUNTER = 2
            REQUEST_SOURCE = 1
            intent = Intent(this, AirportListActivity::class.java)
            startActivity(intent)
        }

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
    companion object{
        var AIRPORT_COUNTER:Int = 0
        var AIRPORT_CODE_FROM = "SUB"
        var AIRPORT_CITY_FROM = "Surabaya"
        var AIRPORT_CODE_TO = "SUB"
        var AIRPORT_CITY_TO = "Surabaya"
    }
}