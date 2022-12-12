package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.model.AccountResponse
import com.ace.c11flight.data.model.LoginInfo
import com.ace.c11flight.data.model.Prefs
import com.ace.c11flight.data.repository.AccountRepository
import com.ace.c11flight.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileActivityViewModel @Inject constructor(
    private val repository: LocalRepository,
    private val accountRepository: AccountRepository
    ) : ViewModel() {
    fun saveLoginStatus(loginStatus: Boolean) {
        viewModelScope.launch {
            repository.setLoginStatus(loginStatus)
        }
    }

    fun getAccountPrefs(): LiveData<Prefs> {
        return repository.getAccountPrefs()
    }

    val _accountData = MutableLiveData<AccountResponse>()
    val accountData: LiveData<AccountResponse>
    get() =_accountData

    fun getUserById() {
        viewModelScope.launch {
            _accountData.postValue(accountRepository.getUserById())
        }
    }
}