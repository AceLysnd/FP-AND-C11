package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.PromoListResponse
import com.ace.c11flight.data.model.TicketListResponse
import com.ace.c11flight.data.model.TicketResponse
import com.ace.c11flight.data.services.AccountApiService
import com.ace.c11flight.ui.view.PromoListActivity
import com.ace.c11flight.ui.view.TicketListActivity
import kotlinx.coroutines.launch

class PromoListViewModel : ViewModel() {

    private val apiService : AccountApiService by lazy {
        AccountApiService.invoke()
    }

    val _promoResult = MutableLiveData<PromoListResponse>()
    val promoResult: LiveData<PromoListResponse>
        get() =_promoResult


    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Pair<Boolean, Exception?>>()

    fun getPromo() {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = apiService.getPromoList()
                viewModelScope.launch {
                    _promoResult.postValue(data)
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