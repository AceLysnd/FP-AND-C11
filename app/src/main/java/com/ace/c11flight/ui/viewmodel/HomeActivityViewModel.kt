package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.Prefs
import com.ace.c11flight.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {
    fun getLoginStatus(): LiveData<Boolean> {
        return repository.getLoginStatus()
    }

    fun getAccountPrefs(): LiveData<Prefs> {
        return repository.getAccountPrefs()
    }

    fun getInAppStatus(): LiveData<Int> {
        return repository.getInAppStatus()
    }

    fun setInAppStatus(inAppStatus: Int) {
        viewModelScope.launch {
            repository.setInAppStatus(inAppStatus)
        }
    }
}