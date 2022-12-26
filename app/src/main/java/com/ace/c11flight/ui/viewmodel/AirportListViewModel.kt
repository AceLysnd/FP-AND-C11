package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.AirportListResponse
import com.ace.c11flight.data.services.AccountApiService
import kotlinx.coroutines.launch

class AirportListViewModel : ViewModel() {

    private val apiService: AccountApiService by lazy {
        AccountApiService.invoke()
    }

    val _airportResult = MutableLiveData<AirportListResponse>()
    val ticketResult: LiveData<AirportListResponse>
        get() = _airportResult

    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Pair<Boolean, Exception?>>()

    fun getAirportList() {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = apiService.getAirportList()
                viewModelScope.launch {
                    _airportResult.postValue(data)
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