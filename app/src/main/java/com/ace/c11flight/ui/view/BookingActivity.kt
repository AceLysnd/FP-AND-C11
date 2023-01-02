package com.ace.c11flight.ui.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ace.c11flight.R
import com.ace.c11flight.data.model.BookingViewModel
import com.ace.c11flight.databinding.ActivityBookingBinding
import com.ace.c11flight.ui.view.HomeFragment.Companion.REQUEST_SOURCE
import com.ace.c11flight.ui.view.SelectDateActivity.Companion.DATE1
import com.ace.c11flight.ui.view.SelectDateActivity.Companion.DATE2

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private val modelBooking: BookingViewModel by viewModels()
    private val modelTeenager: BookingViewModel by viewModels()
    private val modelChild: BookingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        setOnclick()
//        withViewModel()
//        viewmodelTeenager()
//        viewModelChild()
    }


    private fun initData() {
        binding.tvFromCode.text = AIRPORT_CODE_FROM
        binding.etFrom.text = AIRPORT_CITY_FROM
        binding.tvToCode.text = AIRPORT_CODE_TO
        binding.etTo.text = AIRPORT_CITY_TO
    }

    private fun viewModelChild() {
//        binding.btnPlusChildren.setOnClickListener {
//            valuePlusChild()
//        }
//        binding.btnMinusChildreen.setOnClickListener {
//            valueMinChild()
//        }
//        modelChild.chilViewModel.observe(this) { result ->
//            binding.tvValueChildreen.text = result.toString()
//        }
    }

    private fun valueMinChild() {
        modelChild.minvalueChild()
    }

    private fun valuePlusChild() {
        modelChild.plusValuechild()
    }

    private fun viewmodelTeenager() {
//        binding.btnPlusTeenager.setOnClickListener {
//            valuePlusTeenager()
//        }
//        binding.btnMinusTenager.setOnClickListener {
//            valueMinTeenager()
//        }
//        modelTeenager.valueTeenager.observe(this) { result ->
//            binding.tvValueTeenager.text = result.toString()
//        }
    }

    private fun withViewModel() {
//        binding.btnPlusAdult.setOnClickListener {
//            aincrement()
//        }
//        binding.btnMinusPlus.setOnClickListener {
//            bincrement()
//        }
//        modelBooking.valueBooking.observe(this) { result ->
//            binding.tvValueAdult.text = result.toString()
//        }
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

    private fun aincrement() {
        modelBooking.valueBookingPlus()
    }

    private fun setOnclick() {

        binding.btnSearchFlight.setOnClickListener {
//            val valueAdult = binding.tvValueAdult.text.toString()
//            val valueTeenager = binding.tvValueTeenager.text.toString()
//            val valueChild = binding.tvValueChildreen.text.toString()
            PASSENGER_COUNT = 1
//                Integer.parseInt(valueAdult) + Integer.parseInt(valueTeenager) + Integer.parseInt(
//                    valueChild
//                )
            val intent = Intent(this, TicketListActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvFromCode.setOnClickListener {
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
            TYPE_CODE = "1"

            binding.tvReturnDate.isVisible = false
            binding.etReturn.isVisible = false
            binding.ivCallendarReturn.isVisible = false
        }
        binding.btnRoundtrip.setOnClickListener {
            binding.btnRoundtrip.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnOneway.setBackgroundColor(resources.getColor(R.color.grayd9))
            TYPE_CODE = "2"
            binding.tvReturnDate.isVisible = true
            binding.etReturn.isVisible = true
            binding.ivCallendarReturn.isVisible = true
        }
        binding.btnEconomy.setOnClickListener {
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.grayd9))
            CATEGORY_CODE = "Economy"
        }
        binding.btnBussiness.setOnClickListener {
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.grayd9))
            CATEGORY_CODE = "Business"
        }
        binding.btnFirstclass.setOnClickListener {
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.grayd9))
            CATEGORY_CODE = "First Class"
        }

        binding.ivCallendarDepart.setOnClickListener{
            intent = Intent(this, SelectDateActivity::class.java)
            startActivity(intent)
            DATECODE = 0
        }
        binding.ivCallendarReturn.setOnClickListener{
            intent = Intent(this, SelectDateActivity::class.java)
            startActivity(intent)
            DATECODE = 1
        }

        binding.etDepartureDate.text = DATE1
        binding.etReturn.text = DATE2
    }

    companion object {
        var AIRPORT_COUNTER: Int = 0
        var AIRPORT_CODE_FROM = "SUB"
        var AIRPORT_CITY_FROM = "Surabaya"
        var AIRPORT_CODE_TO = "SUB"
        var AIRPORT_CITY_TO = "Surabaya"
        var TYPE_CODE = "0"
        var CATEGORY_CODE = "Economi"
        var PASSENGER_COUNT = 0
        var DATECODE = 0
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}