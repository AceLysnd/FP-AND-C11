package com.ace.c11flight.data.model

import androidx.lifecycle.MutableLiveData

class TeenagerViewModel {
    private val teenagerCounter: MutableLiveData<Int> = MutableLiveData(0)

    fun PlusTeenager() {
        teenagerCounter.postValue(teenagerCounter.value?.plus(1))
    }
    fun MinusTeenager(){
        teenagerCounter.value?.let {
            teenagerCounter.value?.let {
                if (it > 0)
                    teenagerCounter.postValue(teenagerCounter.value?.minus(1))
            }
        }
    }
}