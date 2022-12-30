package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
import com.ace.c11flight.data.model.Promo
import com.ace.c11flight.databinding.ActivityPromoListBinding
import com.ace.c11flight.databinding.ActivityTicketListBinding
import com.ace.c11flight.ui.adapter.PromoAdapter
import com.ace.c11flight.ui.adapter.TicketAdapter
import com.ace.c11flight.ui.viewmodel.PromoListViewModel
import com.ace.c11flight.ui.viewmodel.TicketListActivityViewModel

class PromoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPromoListBinding

    private lateinit var promoAdapter: PromoAdapter
    private lateinit var promoList: RecyclerView

    private val viewModel: PromoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromoListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        promoList = this.findViewById(R.id.rv_promo)
        promoList.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        setAdapter()
        observeData()
    }

    private fun setAdapter() {
        promoAdapter = PromoAdapter(mutableListOf()) { promo -> applyPromo(promo)}
        promoList.adapter = promoAdapter
    }

    private fun applyPromo(promo: Promo) {
        val intent = Intent(this, OrderDetailActivity::class.java)
        APPLIED_PROMO = promo.discount!!
        PROMO_NAME = promo.name!!
        PROMO_ID = promo.id!!

        startActivity(intent)
    }

    private fun observeData() {

        viewModel.getPromo()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.rvPromo.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.promoResult.observe(this) {
            promoAdapter.setItems(it.data)
        }
    }

    companion object {
        var APPLIED_PROMO = 0
        var PROMO_NAME = ""
        var PROMO_ID = 0
    }
}