package com.ace.c11flight.data.services

import android.service.autofill.UserData
import com.ace.c11flight.data.model.LoginInfo
import com.ace.c11flight.data.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccountApiService {

    @Headers("Content-Type: application/json")
    @POST("register")
    fun registerUser(
        @Body userData: UserInfo
    ): Call<UserInfo>

    @POST("login")
    fun loginUser(
        @Body loginData: LoginInfo
    ): Call<LoginInfo>
}