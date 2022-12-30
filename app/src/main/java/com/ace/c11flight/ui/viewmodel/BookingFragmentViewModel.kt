package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.CreateTransactionResponse
import com.ace.c11flight.data.services.AccountApiService
import com.ace.c11flight.ui.viewmodel.TicketListActivityViewModel.Companion.TRANS_ID
import kotlinx.coroutines.launch

class BookingFragmentViewModel : ViewModel() {

    private val apiService: AccountApiService by lazy {
        AccountApiService.invoke()
    }

    val _transactionResult = MutableLiveData<CreateTransactionResponse>()
    val transactionResult: LiveData<CreateTransactionResponse>
        get() = _transactionResult

    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Pair<Boolean, Exception?>>()

    fun getTransaction() {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = apiService.getTransactionById(TRANS_ID)
                viewModelScope.launch {
                    _transactionResult.postValue(data)
                    loadingState.postValue(false)
                    errorState.postValue(Pair(false, null))
                }
            } catch (e: Exception) {
                viewModelScope.launch {
                    loadingState.postValue(false)
                    errorState.postValue(Pair(true, e))
                }
            }
        }
    }
}