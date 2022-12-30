package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityOrderDetailBinding
import com.ace.c11flight.ui.view.BookingActivity.Companion.PASSENGER_COUNT
import com.ace.c11flight.ui.view.BookingActivity.Companion.TYPE_CODE
import com.ace.c11flight.ui.view.ProfileActivity.Companion.ACCOUNT_ID
import com.ace.c11flight.ui.view.PromoListActivity.Companion.APPLIED_PROMO
import com.ace.c11flight.ui.view.PromoListActivity.Companion.PROMO_ID
import com.ace.c11flight.ui.view.PromoListActivity.Companion.PROMO_NAME
import com.ace.c11flight.ui.view.TicketListActivity.Companion.TICKET_ID
import com.ace.c11flight.ui.viewmodel.TicketListActivityViewModel
import com.ace.c11flight.ui.viewmodel.TicketListActivityViewModel.Companion.TRANS_ID
import com.google.gson.annotations.SerializedName
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class OrderDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailBinding

    private val viewModel: TicketListActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
        setOnclickListeners()
        initData()
    }

    private fun initData() {
        if (APPLIED_PROMO != 0){
            binding.applyPromo.isVisible = false
            binding.promoName.isVisible = true
            binding.promoDiscount.isVisible = true
            binding.promoName.text = PROMO_NAME
            binding.promoDiscount.text = "-Rp. " + APPLIED_PROMO.toString()
        }
    }

    private fun setOnclickListeners() {
        binding.wishlist.setOnClickListener{
            binding.wishlist.setImageResource(R.drawable.ic_wishlist_clicked)
        }

        binding.cvPromo.setOnClickListener{
            intent = Intent(this, PromoListActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.proccedToPayment.setOnClickListener{
            val jsonObject = JSONObject()
            jsonObject.put("promo_id", PROMO_ID)
            jsonObject.put("ticket_id",TICKET_ID)
            jsonObject.put("user_id",ACCOUNT_ID)
            jsonObject.put("total", TOTAL_PRICE)


            val jsonObjectString = jsonObject.toString()

            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            viewModel.createTransaction(requestBody)

            viewModel.loadingState.observe(this) { isLoading ->
                binding.pbPost.isVisible = isLoading
            }

            viewModel.errorState.observe(this) { errorData ->
                binding.tvError.isVisible = errorData.first
                errorData.second?.message?.let {
                    binding.tvError.text = it
                }
            }

            viewModel.transactionResult.observe(this) {
                TRANS_ID = it.id
            }

            intent = Intent(this, PaymentPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun observeData() {
        viewModel.getTicketData()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.detailView.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.ticketDetailResult.observe(this) {
            binding.tvFromCode.text = it.data?.flight?.from?.cityCode
            binding.tvFromDesc.text = it.data?.flight?.from?.city
            binding.tvToCode.text = it.data?.flight?.to?.cityCode
            binding.tvToDesc.text = it.data?.flight?.to?.city
            val pricePassenger = it.data?.price
            var totalPricePassenger = pricePassenger?.times(PASSENGER_COUNT)
            val ticketType = Integer.parseInt(TYPE_CODE)
            if (ticketType == 2) {
                binding.tvFlightType.isVisible = true
                binding.tvReturnDate.isVisible = true
                binding.returnDateDesc.isVisible = true
                totalPricePassenger = totalPricePassenger?.times(2)
            }
            TOTAL_PRICE = totalPricePassenger!!
            binding.tvJumlahSatu.text = "Rp. " + totalPricePassenger.toString()
            binding.tvJumlahTiga.text = "-Rp. " + APPLIED_PROMO.toString()
            binding.tvTotal.text = "Rp. " + (totalPricePassenger?.minus(APPLIED_PROMO)).toString()


//            binding.tvJumlahDua.text =
        }
    }
    companion object {
        var TOTAL_PRICE = 0
    }
}