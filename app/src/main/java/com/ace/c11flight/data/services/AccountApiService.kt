package com.ace.c11flight.data.services

import android.service.autofill.UserData
import com.ace.c11flight.data.model.AccountResponse
import com.ace.c11flight.data.model.LoginInfo
import com.ace.c11flight.data.model.UserInfo
import retrofit2.Call
import retrofit2.http.*

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

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long,
    ): AccountResponse
}