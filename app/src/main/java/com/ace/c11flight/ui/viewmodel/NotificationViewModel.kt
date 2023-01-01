package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.NotificationResponse
import com.ace.c11flight.data.model.TicketListResponse
import com.ace.c11flight.data.services.AccountApiService
import com.ace.c11flight.ui.view.ProfileActivity.Companion.ACCOUNT_ID
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel() {

    private val apiService: AccountApiService by lazy {
        AccountApiService.invoke()
    }

    val _notificationResult = MutableLiveData<NotificationResponse>()
    val notificationResult: LiveData<NotificationResponse>
        get() = _notificationResult

    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Pair<Boolean, Exception?>>()

    fun getNotification() {
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = apiService.getNotificationById(ACCOUNT_ID)
                viewModelScope.launch {
                    _notificationResult.postValue(data)
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