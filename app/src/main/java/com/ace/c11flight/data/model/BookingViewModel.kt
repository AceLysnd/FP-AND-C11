package com.ace.c11flight.data.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingViewModel: ViewModel() {
    val valueBooking : MutableLiveData<Int> = MutableLiveData(0)
    val valueTeenager : MutableLiveData<Int> = MutableLiveData(0)
    val chilViewModel : MutableLiveData<Int> = MutableLiveData(0)
    fun valueBookingPlus() {
        valueBooking.postValue(valueBooking?.value?.plus(1))
    }
    fun valueBookingMin() {
        valueBooking.value?.let {
            if (it > 0)
                valueBooking.postValue(valueBooking.value?.minus(1))
        }
    }

    fun plusValueTeenager() {
        valueTeenager.postValue(valueTeenager?.value?.plus(1))
    }

    fun minValueTeenager() {
        valueTeenager.value?.let {
            if(it > 0)
                valueTeenager.postValue((valueTeenager.value?.minus(1)))
        }
    }

    fun plusValuechild() {
        chilViewModel.postValue(chilViewModel?.value?.plus(1))
    }

    fun minvalueChild() {
        chilViewModel.value?.let {
            if (it > 0)
                chilViewModel.postValue(chilViewModel.value?.minus(1))
        }
    }
}