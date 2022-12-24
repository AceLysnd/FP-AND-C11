package com.ace.c11flight.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWishlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}