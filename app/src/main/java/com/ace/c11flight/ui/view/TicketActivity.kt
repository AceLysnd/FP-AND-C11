package com.ace.c11flight.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityTicketBinding

class TicketActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTicketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}