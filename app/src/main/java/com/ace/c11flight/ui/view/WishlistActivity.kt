package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
import com.ace.c11flight.data.local.wishlist.WishlistEntity
import com.ace.c11flight.databinding.ActivityWishlistBinding
import com.ace.c11flight.ui.adapter.WishlistAdapter
import com.ace.c11flight.ui.adapter.WishlistItemListener
import com.ace.c11flight.ui.view.OrderDetailActivity.Companion.TOTAL_PRICE
import com.ace.c11flight.ui.view.ProfileActivity.Companion.ACCOUNT_ID
import com.ace.c11flight.ui.view.PromoListActivity.Companion.PROMO_ID
import com.ace.c11flight.ui.view.TicketListActivity.Companion.TICKET_ID
import com.ace.c11flight.ui.viewmodel.WishlistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWishlistBinding

    private lateinit var wishlistAdapter: WishlistAdapter
    private lateinit var wishlistList: RecyclerView

    private val viewModel: WishlistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wishlistList = this.findViewById(R.id.rv_wishlist)
        wishlistList.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        setAdapter()
        observeData()
    }

    private fun setAdapter() {
        wishlistAdapter = WishlistAdapter(arrayListOf(), object : WishlistItemListener {
            override fun onItemClicked(item: WishlistEntity) {
                goToOrderDetails(item)
            }

            override fun onDeleteMenuClicked(item: WishlistEntity) {
                viewModel.deleteWishlist(item)
                intent = Intent(this@WishlistActivity, WishlistActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        wishlistList.adapter = wishlistAdapter
    }

    private fun goToOrderDetails(wishlist: WishlistEntity) {
        FROM_WISHLIST = true
        PROMO_ID = wishlist.promoId!!
        TICKET_ID = wishlist.ticketId!!
        TOTAL_PRICE = Integer.parseInt(wishlist.price!!)
        intent = Intent(this, OrderDetailActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun observeData() {
        viewModel.getWishlist()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.rvWishlist.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.wishlistResult.observe(this) {
            wishlistAdapter.setItems(it)
        }
    }

    companion object {
        var FROM_WISHLIST = false
    }

}