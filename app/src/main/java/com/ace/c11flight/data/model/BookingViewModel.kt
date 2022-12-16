package com.ace.c11flight.data.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingViewModel: ViewModel() {
    val valueBooking : MutableLiveData<Int> = MutableLiveData(0)
    fun valueBookingPlus() {
        valueBooking.postValue(valueBooking?.value?.plus(1))
    }
    fun valueBookingMin() {
        valueBooking.value?.let {
            if (it > 0)
                valueBooking.postValue(valueBooking.value?.minus(1))
        }
    }
}