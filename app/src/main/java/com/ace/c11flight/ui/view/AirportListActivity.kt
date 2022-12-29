package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
import com.ace.c11flight.data.model.Airport
import com.ace.c11flight.databinding.ActivityAirportListBinding
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CITY_FROM
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CITY_TO
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CODE_FROM
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CODE_TO
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_COUNTER
import com.ace.c11flight.ui.view.HomeFragment.Companion.REQUEST_SOURCE
import com.ace.c11flight.ui.viewmodel.AirportListViewModel
import com.ace.c11flightadmin.ui.adapter.AirportAdapter

class AirportListActivity : AppCompatActivity() {
    private var _binding: ActivityAirportListBinding? = null
    private val binding get() = _binding!!

    private lateinit var airportAdapter: AirportAdapter
    private lateinit var airportList: RecyclerView

    private val viewModel: AirportListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAirportListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        airportList = this.findViewById(R.id.rv_airportList)
        airportList.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        setAdapter()
        observeData()
    }

    private fun observeData() {

        viewModel.getAirportList()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.rvAirportList.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.ticketResult.observe(this) {
            airportAdapter.setItems(it.data)
        }
    }

    private fun setAdapter() {
        airportAdapter = AirportAdapter(mutableListOf()) { airport -> selectAirport(airport) }
        airportList.adapter = airportAdapter
    }

    private fun selectAirport(airport: Airport) {
        if (AIRPORT_COUNTER == 1) {
            AIRPORT_CODE_FROM = airport.cityCode.toString()
            AIRPORT_CITY_FROM = airport.city.toString()
            if (REQUEST_SOURCE == 0) {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                intent = Intent(this, BookingActivity::class.java)
                startActivity(intent)
            }
            finish()
        } else {
            AIRPORT_CODE_TO = airport.cityCode.toString()
            AIRPORT_CITY_TO = airport.city.toString()
            if (REQUEST_SOURCE == 0) {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                intent = Intent(this, BookingActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        intent = Intent(this, BookingActivity::class.java)
        startActivity(intent)
        finish()
    }
}