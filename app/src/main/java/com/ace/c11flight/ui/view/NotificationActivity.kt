package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityNotificationBinding
import com.ace.c11flight.databinding.ActivityTicketBinding
import com.ace.c11flight.ui.adapter.NotificationAdapter
import com.ace.c11flight.ui.adapter.TicketAdapter
import com.ace.c11flight.ui.view.ProfileActivity.Companion.ACCOUNT_ID
import com.ace.c11flight.ui.viewmodel.NotificationViewModel
import com.ace.c11flight.ui.viewmodel.TicketListActivityViewModel

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding

    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notificationList: RecyclerView

    private val viewModel: NotificationViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationList = this.findViewById(R.id.rv_notification)
        notificationList.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        setAdapter()
        observeData()
    }

    private fun observeData() {
        viewModel.getNotification()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.rvNotification.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.notificationResult.observe(this) {
            notificationAdapter.filter.filter(ACCOUNT_ID.toString())
//            ticketAdapter.getSecondFilter().filter(AIRPORT_CODE_TO)
//            ticketAdapter.getThirdFilter().filter("")
            notificationAdapter.setItems(it.data)
//            if(ticketAdapter.itemCount == 0) binding.tvError.isVisible
        }
    }

    private fun setAdapter() {
        notificationAdapter = NotificationAdapter(arrayListOf(), arrayListOf()) { notification -> (notification)}
        notificationList.adapter = notificationAdapter
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}