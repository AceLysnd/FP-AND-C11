package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityBookingBinding
import com.ace.c11flight.databinding.ActivityPaymentPageBinding
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CITY_FROM
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CITY_TO
import com.ace.c11flight.ui.view.BookingActivity.Companion.CATEGORY_CODE
import com.ace.c11flight.ui.view.HomeActivity.Companion.USERNAME
import com.ace.c11flight.ui.view.OrderDetailActivity.Companion.TOTAL_PRICE
import com.ace.c11flight.ui.viewmodel.BookingFragmentViewModel
import com.ace.c11flight.ui.viewmodel.TicketListActivityViewModel

class PaymentPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentPageBinding

    private val viewModel: BookingFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
        setOnclickListeners()
    }

    private fun setOnclickListeners() {
        binding.btnGoToHome.setOnClickListener{
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun observeData() {
        viewModel.getTransaction()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.paymentDetail.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.transactionResult.observe(this) {
            binding.tvTransId.text = "#" + it.data?.id.toString()
            binding.tvTotalDesc.text = "Rp. " + TOTAL_PRICE
            binding.tvFlightDesc.text = "From " + AIRPORT_CITY_FROM + " To " + AIRPORT_CITY_TO
            binding.tvUsernameDesc.text = USERNAME
            val ticketType = Integer.parseInt(BookingActivity.TYPE_CODE)
            if (ticketType == 2) {
                binding.tvTypeDesc.text = "Round Trip, " + CATEGORY_CODE
            } else {
                binding.tvTypeDesc.text = "One Way, " + CATEGORY_CODE
            }

        }
    }
}