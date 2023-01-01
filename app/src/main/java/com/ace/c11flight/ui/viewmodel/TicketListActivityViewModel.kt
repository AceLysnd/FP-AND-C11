package com.ace.c11flight.ui.viewmodel

import com.ace.c11flight.data.model.TicketListResponse
import com.ace.c11flight.data.services.AccountApiService

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.CreateTransactionResponse
import com.ace.c11flight.data.model.TicketResponse
import com.ace.c11flight.data.model.Transaction
import com.ace.c11flight.data.repository.LocalRepository
import com.ace.c11flight.ui.view.ProfileActivity
import com.ace.c11flight.ui.view.TicketListActivity.Companion.TICKET_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class TicketListActivityViewModel @Inject constructor(
    private val repository: LocalRepository

): ViewModel() {

    private val apiService : AccountApiService by lazy {
        AccountApiService.invoke()
    }

    fun getInAppStatus(): LiveData<Int> {
        return repository.getInAppStatus()
    }

    fun setInAppStatus(inAppStatus: Int) {
        viewModelScope.launch {
            repository.setInAppStatus(inAppStatus)
        }
    }

    val _ticketResult = MutableLiveData<TicketListResponse>()
    val ticketResult: LiveData<TicketListResponse>
        get() =_ticketResult

    val _ticketDetailResult = MutableLiveData<TicketResponse>()
    val ticketDetailResult: LiveData<TicketResponse>
        get() =_ticketDetailResult

    val _transactionResult = MutableLiveData<Transaction>()
    val transactionResult: LiveData<Transaction>
        get() = _transactionResult

    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Pair<Boolean, Exception?>>()

    fun getTickets() {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = apiService.getTickets()
                viewModelScope.launch {
                    _ticketResult.postValue(data)
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

    fun getTicketData() {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val dataDetail = apiService.getTicketById(TICKET_ID)
                viewModelScope.launch {
                    _ticketDetailResult.postValue(dataDetail)
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

    fun createTransaction(data: RequestBody) {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val getData = apiService.createTransaction(data)
                viewModelScope.launch {
                    _transactionResult.postValue(getData)
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
    companion object {
        var TRANS_ID:Int? = 0
    }
}