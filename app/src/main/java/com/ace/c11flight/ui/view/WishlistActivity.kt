package com.ace.c11flight.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWishlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        val recyleView = findViewById<RecyclerView>(R.id.rv_wishlist)

        recyleView.layoutManager = layoutManager
        recyleView.adapter = null
    }

}