package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.Transaction
import com.ace.c11flight.data.model.TransactionListResponse
import com.ace.c11flight.data.services.AccountApiService
import kotlinx.coroutines.launch

class TransactionHistoryViewModel : ViewModel() {

    private val apiService : AccountApiService by lazy {
        AccountApiService.invoke()
    }

    val _transactionResult = MutableLiveData<TransactionListResponse>()
    val transactionResult: LiveData<TransactionListResponse>
        get() = _transactionResult

    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Pair<Boolean, Exception?>>()

    fun getTransactions() {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = apiService.getTransactionList()
                viewModelScope.launch {
                    _transactionResult.postValue(data)
                    loadingState.postValue(false)
                    errorState.postValue(Pair(false,null))
                }
            } catch (e: Exception) {
                viewModelScope.launch {
                    loadingState.postValue(false)
                    errorState.postValue(Pair(true,e))
                }
            }
        }
    }
}