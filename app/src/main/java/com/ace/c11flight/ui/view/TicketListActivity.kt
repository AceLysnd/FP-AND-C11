package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
import com.ace.c11flight.data.model.Ticket
import com.ace.c11flight.databinding.ActivityTicketListBinding
import com.ace.c11flight.ui.adapter.TicketAdapter
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CODE_FROM
import com.ace.c11flight.ui.viewmodel.TicketListActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketListBinding

    private lateinit var ticketAdapter: TicketAdapter
    private lateinit var ticketList: RecyclerView

    private val viewModel: TicketListActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityTicketListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ticketList = this.findViewById(R.id.rv_tickets)
        ticketList.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        setAdapter()
        observeData()
    }


    private fun setAdapter() {
        ticketAdapter = TicketAdapter(arrayListOf(), arrayListOf()) { ticket -> goToOrderDetails(ticket)}
        ticketList.adapter = ticketAdapter
    }

    private fun goToOrderDetails(ticket: Ticket) {
        val intent = Intent(this, OrderDetailActivity::class.java)
        TICKET_ID = ticket.id!!

        startActivity(intent)
    }

    private fun observeData() {

        viewModel.getTickets()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.rvTickets.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.ticketResult.observe(this) {
            ticketAdapter.filter.filter(AIRPORT_CODE_FROM)
//            ticketAdapter.getSecondFilter().filter(AIRPORT_CODE_TO)
//            ticketAdapter.getThirdFilter().filter("")
            ticketAdapter.setItems(it.data)
            if(ticketAdapter.itemCount == 0) binding.tvError.isVisible
        }
    }

    companion object {
        var TICKET_ID: Int = 0
    }
}