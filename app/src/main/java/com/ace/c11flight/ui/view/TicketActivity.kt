package com.ace.c11flight.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.ace.c11flight.R
//import com.ace.c11flight.data.model.AdapterWislit
import com.ace.c11flight.databinding.ActivityTicketBinding

class TicketActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTicketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}