package com.ace.c11flight.ui.viewmodel

import com.ace.c11flight.data.model.TicketListResponse
import com.ace.c11flight.data.services.AccountApiService

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.TicketResponse
import com.ace.c11flight.ui.view.TicketListActivity.Companion.TICKET_ID
import kotlinx.coroutines.launch

class TicketListActivityViewModel : ViewModel() {

    private val apiService : AccountApiService by lazy {
        AccountApiService.invoke()
    }

    val _ticketResult = MutableLiveData<TicketListResponse>()
    val ticketResult: LiveData<TicketListResponse>
        get() =_ticketResult

    val _ticketDetailResult = MutableLiveData<TicketResponse>()
    val ticketDetailResult: LiveData<TicketResponse>
        get() =_ticketDetailResult

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
}