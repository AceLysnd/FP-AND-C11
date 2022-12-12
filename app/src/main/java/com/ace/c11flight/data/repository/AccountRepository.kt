package com.ace.c11flight.data.repository

import com.ace.c11flight.data.services.TempApiHelper
import com.ace.c11flight.ui.view.ProfileActivity.Companion.ACCOUNT_ID

class AccountRepository(private val apiHelper: TempApiHelper){

    suspend fun getUserById() = apiHelper.getUserById(ACCOUNT_ID)
}