package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.local.user.AccountEntity
import com.ace.c11flight.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterActivityViewModel @Inject constructor(
    private val repository: LocalRepository
    ) : ViewModel() {

    fun registerUser(account: AccountEntity) {
        viewModelScope.launch {
            repository.createAccount(account)
        }
    }
}