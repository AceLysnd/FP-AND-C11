package com.ace.c11flight.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityTransactionHistoryBinding
import com.ace.c11flight.ui.adapter.TransactionHistoryAdapter
import com.ace.c11flight.ui.view.HomeActivity.Companion.USERNAME
import com.ace.c11flight.ui.view.ProfileActivity.Companion.ACCOUNT_ID
import com.ace.c11flight.ui.viewmodel.TransactionHistoryViewModel

class TransactionHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionHistoryBinding

    private lateinit var transactionAdapter: TransactionHistoryAdapter
    private lateinit var transactionList: RecyclerView

    private val viewModel: TransactionHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transactionList = this.findViewById(R.id.rv_transactions)
        transactionList.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        setAdapter()
        observeData()
    }

    private fun setAdapter() {
        transactionAdapter = TransactionHistoryAdapter(arrayListOf(), arrayListOf()) {
//                ticket -> goToOrderDetails(ticket)
        }
        transactionList.adapter = transactionAdapter
    }

    private fun observeData() {

        viewModel.getTransactions()

        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
            binding.rvTransactions.isVisible = !isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }

        viewModel.transactionResult.observe(this) {
            transactionAdapter.filter.filter(ACCOUNT_ID.toString())
//            ticketAdapter.getSecondFilter().filter(AIRPORT_CODE_TO)
//            ticketAdapter.getThirdFilter().filter("")
            transactionAdapter.addItem(it.data)
//            if(transactionAdapter.itemCount == 0) binding.tvError.isVisible = true
        }
    }
}